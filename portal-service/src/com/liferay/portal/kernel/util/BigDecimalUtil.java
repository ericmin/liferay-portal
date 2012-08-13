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

package com.liferay.portal.kernel.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class BigDecimalUtil {

	public static double add(double x, double y) {
		BigDecimal xBigDecimal = new BigDecimal(String.valueOf(x));
		BigDecimal yBigDecimal = new BigDecimal(String.valueOf(y));

		BigDecimal resultBigDecimal = xBigDecimal.add(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

	public static double divide(
		double x, double y, int scale, RoundingMode roundingMode) {

		BigDecimal xBigDecimal = new BigDecimal(String.valueOf(x));
		BigDecimal yBigDecimal = new BigDecimal(String.valueOf(y));

		BigDecimal resultBigDecimal = xBigDecimal.divide(
			yBigDecimal, scale, roundingMode);

		return resultBigDecimal.doubleValue();
	}

	public static double divide(
		int x, int y, int scale, RoundingMode roundingMode) {

		BigDecimal xBigDecimal = new BigDecimal(String.valueOf(x));
		BigDecimal yBigDecimal = new BigDecimal(String.valueOf(y));

		BigDecimal resultBigDecimal = xBigDecimal.divide(
			yBigDecimal, scale, roundingMode);

		return resultBigDecimal.doubleValue();
	}

	public static double multiply(double x, double y) {
		BigDecimal xBigDecimal = new BigDecimal(String.valueOf(x));
		BigDecimal yBigDecimal = new BigDecimal(String.valueOf(y));

		BigDecimal resultBigDecimal = xBigDecimal.multiply(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

	public static double scale(double x, int scale, RoundingMode roundingMode) {
		BigDecimal xBigDecimal = new BigDecimal(String.valueOf(x));

		xBigDecimal = xBigDecimal.setScale(scale, roundingMode);

		return xBigDecimal.doubleValue();
	}

	public static double subtract(double x, double y) {
		BigDecimal xBigDecimal = new BigDecimal(String.valueOf(x));
		BigDecimal yBigDecimal = new BigDecimal(String.valueOf(y));

		BigDecimal resultBigDecimal = xBigDecimal.subtract(yBigDecimal);

		return resultBigDecimal.doubleValue();
	}

}