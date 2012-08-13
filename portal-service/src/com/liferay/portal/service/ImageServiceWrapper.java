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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link ImageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ImageService
 * @generated
 */
public class ImageServiceWrapper implements ImageService,
	ServiceWrapper<ImageService> {
	public ImageServiceWrapper(ImageService imageService) {
		_imageService = imageService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _imageService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_imageService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.Image getImage(long imageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _imageService.getImage(imageId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ImageService getWrappedImageService() {
		return _imageService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedImageService(ImageService imageService) {
		_imageService = imageService;
	}

	public ImageService getWrappedService() {
		return _imageService;
	}

	public void setWrappedService(ImageService imageService) {
		_imageService = imageService;
	}

	private ImageService _imageService;
}