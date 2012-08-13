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

package com.liferay.portlet.polls.action;

import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.polls.util.PollsUtil;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewChartAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			long questionId = ParamUtil.getLong(request, "questionId");

			String chartType = ParamUtil.getString(request, "chartType", "pie");

			CategoryDataset dataset = PollsUtil.getVotesDataset(questionId);

			String chartName = themeDisplay.translate("vote-results");
			String xName = themeDisplay.translate("choice");
			String yName = themeDisplay.translate("votes");

			JFreeChart chart = null;

			if (chartType.equals("area")) {
				chart = ChartFactory.createAreaChart(
					chartName, xName, yName, dataset, PlotOrientation.VERTICAL,
					true, false, false);
			}
			else if (chartType.equals("horizontal_bar")) {
				chart = ChartFactory.createBarChart(
					chartName, xName, yName, dataset,
					PlotOrientation.HORIZONTAL, true, false, false);
			}
			else if (chartType.equals("line")) {
				chart = ChartFactory.createLineChart(
					chartName, xName, yName, dataset, PlotOrientation.VERTICAL,
					true, false, false);
			}
			else if (chartType.equals("vertical_bar")) {
				chart = ChartFactory.createBarChart(
					chartName, xName, yName, dataset, PlotOrientation.VERTICAL,
					true, false, false);
			}
			else {
				PieDataset pieData = DatasetUtilities.createPieDatasetForRow(
					dataset, 0);

				chart = ChartFactory.createPieChart(
					chartName, pieData, true, false, false);
			}

			response.setContentType(ContentTypes.IMAGE_JPEG);

			OutputStream os = response.getOutputStream();

			ChartUtilities.writeChartAsJPEG(os, chart, 400, 400);

			return null;
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

}