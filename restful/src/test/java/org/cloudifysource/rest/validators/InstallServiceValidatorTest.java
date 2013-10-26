/*******************************************************************************
 * Copyright (c) 2013 GigaSpaces Technologies Ltd. All rights reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *******************************************************************************/
package org.cloudifysource.rest.validators;

import java.io.File;
import java.io.IOException;

import org.cloudifysource.domain.Service;
import org.cloudifysource.domain.cloud.Cloud;
import org.cloudifysource.dsl.rest.request.InstallServiceRequest;
import org.cloudifysource.rest.controllers.RestErrorException;
import org.junit.Assert;

/**
 * 
 * @author yael
 * 
 */
public abstract class InstallServiceValidatorTest {

	private InstallServiceRequest request;
	private Cloud cloud;
	private Service service;
	private File cloudOverridesFile;
	private File serviceOverridesFile;
	private File cloudConfigurationFile;
	private String exceptionCause;
	private boolean debugAll;
	private String debugMode;
	private String debugEvents;


	public abstract InstallServiceValidator getValidatorInstance();

	public void init() throws IOException {
		
	}

	/**
	 * 
	 * @param request
	 *            .
	 * @param cloud
	 *            .
	 * @param service
	 *            .
	 * @param cloudOverridesFile
	 *            .
	 * @param serviceOverridesFile
	 *            .
	 * @param cloudConfigurationFile
	 *            .
	 * @param exceptionCause
	 *            .
	 * @throws IOException
	 */
	public void testValidator() throws IOException {
		init();
		final InstallServiceValidator validator = getValidatorInstance();
		final InstallServiceValidationContext validationContext = new InstallServiceValidationContext();
		validationContext.setCloud(cloud);
		validationContext.setService(service);
		validationContext.setCloudOverridesFile(cloudOverridesFile);
		validationContext.setServiceOverridesFile(serviceOverridesFile);
		validationContext.setCloudConfigurationFile(cloudConfigurationFile);
		validationContext.setDebugAll(debugAll);
		validationContext.setDebugMode(debugMode);
		validationContext.setDebugEvents(debugEvents);
		try {
			validator.validate(validationContext);
			if (exceptionCause != null) {
				Assert.fail(exceptionCause + " didn't yield the expected RestErrorException.");
			}
		} catch (final RestErrorException e) {
			if (exceptionCause == null) {
				e.printStackTrace();
				Assert.fail();
			}
			Assert.assertEquals(exceptionCause, e.getMessage());
		}
	}

	public final InstallServiceRequest getRequest() {
		return request;
	}

	public final void setRequest(final InstallServiceRequest request) {
		this.request = request;
	}

	public final Cloud getCloud() {
		return cloud;
	}

	public final void setCloud(final Cloud cloud) {
		this.cloud = cloud;
	}

	public final Service getService() {
		return service;
	}

	public final void setService(final Service service) {
		this.service = service;
	}

	public final File getCloudOverridesFile() {
		return cloudOverridesFile;
	}

	public final void setCloudOverridesFile(final File cloudOverridesFile) {
		this.cloudOverridesFile = cloudOverridesFile;
	}

	public final File getServiceOverridesFile() {
		return serviceOverridesFile;
	}

	public final void setServiceOverridesFile(final File serviceOverridesFile) {
		this.serviceOverridesFile = serviceOverridesFile;
	}

	public final File getCloudConfigurationFile() {
		return cloudConfigurationFile;
	}

	public final void setCloudConfigurationFile(final File cloudConfigurationFile) {
		this.cloudConfigurationFile = cloudConfigurationFile;
	}

	public final String getExceptionCause() {
		return exceptionCause;
	}

	public final void setExceptionCause(final String exceptionCause) {
		this.exceptionCause = exceptionCause;
	}

	public final boolean isDebugAll() {
		return debugAll;
	}

	public final void setDebugAll(final boolean debugAll) {
		this.debugAll = debugAll;
	}

	public final String getDebugMode() {
		return debugMode;
	}

	public final void setDebugMode(final String debugMode) {
		this.debugMode = debugMode;
	}

	public final String getDebugEvents() {
		return debugEvents;
	}

	public final void setDebugEvents(final String debugEvents) {
		this.debugEvents = debugEvents;
	}
}
