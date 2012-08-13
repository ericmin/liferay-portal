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

package com.liferay.portlet.shopping.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.AssertUtils;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;

import com.liferay.portlet.shopping.NoSuchItemPriceException;
import com.liferay.portlet.shopping.model.ShoppingItemPrice;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class ShoppingItemPricePersistenceTest {
	@After
	public void tearDown() throws Exception {
		Map<Serializable, BasePersistence<?>> basePersistences = _transactionalPersistenceAdvice.getBasePersistences();

		Set<Serializable> primaryKeys = basePersistences.keySet();

		for (Serializable primaryKey : primaryKeys) {
			BasePersistence<?> basePersistence = basePersistences.get(primaryKey);

			try {
				basePersistence.remove(primaryKey);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("The model with primary key " + primaryKey +
						" was already deleted");
				}
			}
		}

		_transactionalPersistenceAdvice.reset();
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingItemPrice shoppingItemPrice = _persistence.create(pk);

		Assert.assertNotNull(shoppingItemPrice);

		Assert.assertEquals(shoppingItemPrice.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ShoppingItemPrice newShoppingItemPrice = addShoppingItemPrice();

		_persistence.remove(newShoppingItemPrice);

		ShoppingItemPrice existingShoppingItemPrice = _persistence.fetchByPrimaryKey(newShoppingItemPrice.getPrimaryKey());

		Assert.assertNull(existingShoppingItemPrice);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addShoppingItemPrice();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingItemPrice newShoppingItemPrice = _persistence.create(pk);

		newShoppingItemPrice.setItemId(ServiceTestUtil.nextLong());

		newShoppingItemPrice.setMinQuantity(ServiceTestUtil.nextInt());

		newShoppingItemPrice.setMaxQuantity(ServiceTestUtil.nextInt());

		newShoppingItemPrice.setPrice(ServiceTestUtil.nextDouble());

		newShoppingItemPrice.setDiscount(ServiceTestUtil.nextDouble());

		newShoppingItemPrice.setTaxable(ServiceTestUtil.randomBoolean());

		newShoppingItemPrice.setShipping(ServiceTestUtil.nextDouble());

		newShoppingItemPrice.setUseShippingFormula(ServiceTestUtil.randomBoolean());

		newShoppingItemPrice.setStatus(ServiceTestUtil.nextInt());

		_persistence.update(newShoppingItemPrice, false);

		ShoppingItemPrice existingShoppingItemPrice = _persistence.findByPrimaryKey(newShoppingItemPrice.getPrimaryKey());

		Assert.assertEquals(existingShoppingItemPrice.getItemPriceId(),
			newShoppingItemPrice.getItemPriceId());
		Assert.assertEquals(existingShoppingItemPrice.getItemId(),
			newShoppingItemPrice.getItemId());
		Assert.assertEquals(existingShoppingItemPrice.getMinQuantity(),
			newShoppingItemPrice.getMinQuantity());
		Assert.assertEquals(existingShoppingItemPrice.getMaxQuantity(),
			newShoppingItemPrice.getMaxQuantity());
		AssertUtils.assertEquals(existingShoppingItemPrice.getPrice(),
			newShoppingItemPrice.getPrice());
		AssertUtils.assertEquals(existingShoppingItemPrice.getDiscount(),
			newShoppingItemPrice.getDiscount());
		Assert.assertEquals(existingShoppingItemPrice.getTaxable(),
			newShoppingItemPrice.getTaxable());
		AssertUtils.assertEquals(existingShoppingItemPrice.getShipping(),
			newShoppingItemPrice.getShipping());
		Assert.assertEquals(existingShoppingItemPrice.getUseShippingFormula(),
			newShoppingItemPrice.getUseShippingFormula());
		Assert.assertEquals(existingShoppingItemPrice.getStatus(),
			newShoppingItemPrice.getStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ShoppingItemPrice newShoppingItemPrice = addShoppingItemPrice();

		ShoppingItemPrice existingShoppingItemPrice = _persistence.findByPrimaryKey(newShoppingItemPrice.getPrimaryKey());

		Assert.assertEquals(existingShoppingItemPrice, newShoppingItemPrice);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchItemPriceException");
		}
		catch (NoSuchItemPriceException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ShoppingItemPrice newShoppingItemPrice = addShoppingItemPrice();

		ShoppingItemPrice existingShoppingItemPrice = _persistence.fetchByPrimaryKey(newShoppingItemPrice.getPrimaryKey());

		Assert.assertEquals(existingShoppingItemPrice, newShoppingItemPrice);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingItemPrice missingShoppingItemPrice = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingShoppingItemPrice);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ShoppingItemPrice newShoppingItemPrice = addShoppingItemPrice();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingItemPrice.class,
				ShoppingItemPrice.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPriceId",
				newShoppingItemPrice.getItemPriceId()));

		List<ShoppingItemPrice> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ShoppingItemPrice existingShoppingItemPrice = result.get(0);

		Assert.assertEquals(existingShoppingItemPrice, newShoppingItemPrice);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingItemPrice.class,
				ShoppingItemPrice.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPriceId",
				ServiceTestUtil.nextLong()));

		List<ShoppingItemPrice> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ShoppingItemPrice newShoppingItemPrice = addShoppingItemPrice();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingItemPrice.class,
				ShoppingItemPrice.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("itemPriceId"));

		Object newItemPriceId = newShoppingItemPrice.getItemPriceId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPriceId",
				new Object[] { newItemPriceId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemPriceId = result.get(0);

		Assert.assertEquals(existingItemPriceId, newItemPriceId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingItemPrice.class,
				ShoppingItemPrice.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("itemPriceId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPriceId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ShoppingItemPrice addShoppingItemPrice()
		throws Exception {
		long pk = ServiceTestUtil.nextLong();

		ShoppingItemPrice shoppingItemPrice = _persistence.create(pk);

		shoppingItemPrice.setItemId(ServiceTestUtil.nextLong());

		shoppingItemPrice.setMinQuantity(ServiceTestUtil.nextInt());

		shoppingItemPrice.setMaxQuantity(ServiceTestUtil.nextInt());

		shoppingItemPrice.setPrice(ServiceTestUtil.nextDouble());

		shoppingItemPrice.setDiscount(ServiceTestUtil.nextDouble());

		shoppingItemPrice.setTaxable(ServiceTestUtil.randomBoolean());

		shoppingItemPrice.setShipping(ServiceTestUtil.nextDouble());

		shoppingItemPrice.setUseShippingFormula(ServiceTestUtil.randomBoolean());

		shoppingItemPrice.setStatus(ServiceTestUtil.nextInt());

		_persistence.update(shoppingItemPrice, false);

		return shoppingItemPrice;
	}

	private static Log _log = LogFactoryUtil.getLog(ShoppingItemPricePersistenceTest.class);
	private ShoppingItemPricePersistence _persistence = (ShoppingItemPricePersistence)PortalBeanLocatorUtil.locate(ShoppingItemPricePersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}