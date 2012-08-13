/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.util.PropsValues;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class BatchSessionImpl implements BatchSession {

	public void delete(Session session, BaseModel<?> model)
		throws ORMException {

		if (model.isCachedModel() || isEnabled()) {
			Object staleObject = session.get(
				model.getClass(), model.getPrimaryKeyObj());

			if (staleObject != null) {
				session.evict(staleObject);
			}
		}

		session.delete(model);

		if (!isEnabled()) {
			session.flush();

			return;
		}

		if ((PropsValues.HIBERNATE_JDBC_BATCH_SIZE == 0) ||
			((_counter.get() % PropsValues.HIBERNATE_JDBC_BATCH_SIZE) == 0)) {

			session.flush();
		}

		_counter.set(_counter.get() + 1);
	}

	public boolean isEnabled() {
		return _enabled.get();
	}

	public void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	public void update(Session session, BaseModel<?> model, boolean merge)
		throws ORMException {

		if (merge || model.isCachedModel()) {
			session.merge(model);
		}
		else {
			if (model.isNew()) {
				session.save(model);
			}
			else {
				boolean contains = false;

				if (isEnabled()) {
					Object obj = session.get(
						model.getClass(), model.getPrimaryKeyObj());

					if ((obj != null) && obj.equals(model)) {
						contains = true;
					}
				}

				if (!contains && !session.contains(model)) {
					session.saveOrUpdate(model);
				}
			}
		}

		if (!isEnabled()) {
			session.flush();

			return;
		}

		if ((PropsValues.HIBERNATE_JDBC_BATCH_SIZE == 0) ||
			((_counter.get() % PropsValues.HIBERNATE_JDBC_BATCH_SIZE) == 0)) {

			session.flush();
		}

		_counter.set(_counter.get() + 1);
	}

	private static final long _INITIAL_COUNTER = 1;

	private static ThreadLocal<Long> _counter = new InitialThreadLocal<Long>(
		BatchSessionImpl.class + "._counter", _INITIAL_COUNTER);
	private static ThreadLocal<Boolean> _enabled =
		new InitialThreadLocal<Boolean>(
			BatchSessionImpl.class + "._enabled", false);

}