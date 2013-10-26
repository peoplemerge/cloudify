/*******************************************************************************
 * Copyright (c) 2013 GigaSpaces Technologies Ltd. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.cloudifysource.rest.validators;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.cloudifysource.dsl.internal.CloudifyMessageKeys;
import org.junit.Test;

public class ValidateServiceOverridesFileSizeTest extends InstallServiceValidatorTest {

    private ValidateOverridesFileSize validator;
    private static final long TEST_FILE_SIZE_LIMIT = 3;

    private File serviceOverrides;
    
    @Override
    public void init() throws IOException {
        validator = new ValidateOverridesFileSize();
        validator.setServiceOverridesFileSizeLimit(TEST_FILE_SIZE_LIMIT);
        serviceOverrides = File.createTempFile("serviceOverrides", "");
        FileUtils.writeStringToFile(serviceOverrides, "I'm longer than 3 bytes !");
        setServiceOverridesFile(serviceOverrides);
        setExceptionCause(CloudifyMessageKeys.SERVICE_OVERRIDES_SIZE_LIMIT_EXCEEDED.getName());
    }

    @Test
    public void testSizeLimitExeeded() throws IOException {
    	testValidator();
        serviceOverrides.delete();
    }

    @Override
    public InstallServiceValidator getValidatorInstance() {
        return validator;
    }

}
