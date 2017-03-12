/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2016 The ZAP development team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.zap.extension.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.junit.Test;
import org.zaproxy.zap.utils.ZapXmlConfiguration;

/**
 * Unit test for {@link OptionsParamApi}.
 */
public class OptionsParamApiUnitTest {

    private static final String API_ENABLED_KEY = "api.enabled";
    private static final String API_SECURE_KEY = "api.secure";
    private static final String API_KEY_KEY = "api.key";
    private static final String API_DISABLEKEY_KEY = "api.disablekey";
    private static final String API_INCERRORDETAILS_KEY = "api.incerrordetails";
    private static final String API_AUTOFILLKEY_KEY = "api.autofillkey";
    private static final String API_ENABLEJSONP_KEY = "api.enablejsonp";
	private static final String API_NO_KEY_FOR_SAFE_OPS = "api.nokeyforsafeops";
	private static final String API_REPORT_PERM_ERRORS = "api.reportpermerrors";

    @Test
    public void shouldNotHaveConfigByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.getConfig(), is(equalTo(null)));
    }

    @Test
    public void shouldHaveEnabledStateByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isEnabled(), is(equalTo(true)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetEnabledStateWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setEnabled(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetEnabledStateWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setEnabled(false);
        // Then
        assertThat(param.isEnabled(), is(equalTo(false)));
        assertThat(param.getConfig().getBoolean(API_ENABLED_KEY), is(equalTo(false)));
    }

    @Test
    public void shouldHaveSecureOnlyDisabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isSecureOnly(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetSecureOnlyWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setSecureOnly(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetSecureOnlyWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setSecureOnly(true);
        // Then
        assertThat(param.isSecureOnly(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_SECURE_KEY), is(equalTo(true)));
    }

    @Test
    public void shouldHaveKeyEnabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isDisableKey(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetDisableKeyWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setDisableKey(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetDisableKeyWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setDisableKey(true);
        // Then
        assertThat(param.isEnabled(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_DISABLEKEY_KEY), is(equalTo(true)));
    }

    @Test
    public void shouldHaveIncErrorDetailsDisabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isIncErrorDetails(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetIncErrorDetailsWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setIncErrorDetails(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetIncErrorDetailsWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setIncErrorDetails(true);
        // Then
        assertThat(param.isIncErrorDetails(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_INCERRORDETAILS_KEY), is(equalTo(true)));
    }

    @Test
    public void shouldHaveAutofillKeyDisabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isAutofillKey(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetAutofillKeyWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setAutofillKey(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetAutofillKeyWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setAutofillKey(true);
        // Then
        assertThat(param.isAutofillKey(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_AUTOFILLKEY_KEY), is(equalTo(true)));
    }

    @Test
    public void shouldHaveEnableJSONPDisabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isEnableJSONP(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetEnableJSONPWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setEnableJSONP(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetEnableJSONPWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setEnableJSONP(true);
        // Then
        assertThat(param.isEnableJSONP(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_ENABLEJSONP_KEY), is(equalTo(true)));
    }

    @Test
    public void shouldHaveReportPermErrorsDisabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isReportPermErrors(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetReportPermErrorsWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setReportPermErrors(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetReportPermErrorsWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setReportPermErrors(true);
        // Then
        assertThat(param.isReportPermErrors(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_REPORT_PERM_ERRORS), is(equalTo(true)));
    }

    @Test
    public void shouldHaveNonceTimeToLiveInSecsSetTo5MinsByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.getNonceTimeToLiveInSecs(), is(equalTo(5 * 60)));
    }

    @Test
    public void shouldHaveNoKeyForViewsOrSafeOthersDisabledByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.isNoKeyForSafeOps(), is(equalTo(false)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetNoKeyForViewsOrSafeOthersWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setNoKeyForSafeOps(true);
        // Then = NullPointerException
    }

    @Test
    public void shouldSetNoKeyForViewsOrSafeOthersWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        // When
        param.setNoKeyForSafeOps(true);
        // Then
        assertThat(param.isNoKeyForSafeOps(), is(equalTo(true)));
        assertThat(param.getConfig().getBoolean(API_NO_KEY_FOR_SAFE_OPS), is(equalTo(true)));
    }

    @Test
    public void shouldHaveEmptyRealKeyByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.getRealKey(), isEmptyString());
    }

    @Test
    public void shouldHaveGeneratedKeyByDefault() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // Then
        assertThat(param.getKey(), is(not(equalTo(""))));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailToSetKeyWithoutConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        // When
        param.setKey("");
        // Then = NullPointerException
    }

    @Test
    public void shouldSetKeyWithConfig() {
        // Given / When
        OptionsParamApi param = createOptionsParamApiWithConfig();
        String apiKey = "Key";
        // When
        param.setKey(apiKey);
        // Then
        assertThat(param.getKey(), is(equalTo(apiKey)));
        assertThat(param.getConfig().getString(API_KEY_KEY), is(equalTo(apiKey)));
    }

    @Test
    public void shouldSaveGeneratedKeyWithConfig() {
        // Given / When
        OptionsParamApi param = new OptionsParamApi();
        Configuration conf = new Configuration();
        param.load(conf);
        param.setKey(null);
        // When
        String key = param.getKey();
        // Then
        assertThat(key, is(not(equalTo(""))));
        assertThat(conf.getString(API_KEY_KEY), is(equalTo(key)));
        assertThat(conf.isSaved(), is(equalTo(true)));
    }

    @Test
    public void shouldReturnEmptyKeyIfKeyDisabled() {
        // Given
        OptionsParamApi param = createOptionsParamApiWithConfig();
        param.setDisableKey(true);
        param.setKey("Key");
        // When
        String key = param.getKey();
        // Then
        assertThat(key, is(equalTo("")));
        assertThat(param.getRealKey(), is(equalTo("Key")));
    }

    @Test
    public void shouldParseLoadedFileConfiguration() {
        // Given
        OptionsParamApi param = new OptionsParamApi();
        FileConfiguration config = createTestConfig();
        // When
        param.load(config);
        // Then
        assertThat(param.isEnabled(), is(equalTo(false)));
        assertThat(param.isSecureOnly(), is(equalTo(true)));
        assertThat(param.isDisableKey(), is(equalTo(true)));
        assertThat(param.isIncErrorDetails(), is(equalTo(true)));
        assertThat(param.isAutofillKey(), is(equalTo(true)));
        assertThat(param.isEnableJSONP(), is(equalTo(true)));
        assertThat(param.getRealKey(), is(equalTo("ApiKey")));
    }

    @Test
    public void shouldBeCloneableByDefault() {
        // Given
        OptionsParamApi param = new OptionsParamApi();
        // When
        OptionsParamApi clone = param.clone();
        // Then
        assertThat(clone, is(notNullValue()));
        assertThat(param.isEnabled(), is(equalTo(true)));
        assertThat(param.isSecureOnly(), is(equalTo(false)));
        assertThat(param.isDisableKey(), is(equalTo(false)));
        assertThat(param.isIncErrorDetails(), is(equalTo(false)));
        assertThat(param.isAutofillKey(), is(equalTo(false)));
        assertThat(param.isEnableJSONP(), is(equalTo(false)));
        assertThat(param.getRealKey(), is(equalTo("")));
    }

    @Test
    public void shouldHaveLoadedConfigsAfterCloning() {
        // Given
        OptionsParamApi param = new OptionsParamApi();
        FileConfiguration config = createTestConfig();
        param.load(config);
        // When
        OptionsParamApi clone = param.clone();
        // Then
        assertThat(clone, is(notNullValue()));
        assertThat(param.isEnabled(), is(equalTo(false)));
        assertThat(param.isSecureOnly(), is(equalTo(true)));
        assertThat(param.isDisableKey(), is(equalTo(true)));
        assertThat(param.isIncErrorDetails(), is(equalTo(true)));
        assertThat(param.isAutofillKey(), is(equalTo(true)));
        assertThat(param.isEnableJSONP(), is(equalTo(true)));
        assertThat(param.getRealKey(), is(equalTo("ApiKey")));
    }

    @Test
    public void shouldUseDefaultValuesWhenLoadingFileConfigurationWithInvalidValues() {
        // Given
        OptionsParamApi param = new OptionsParamApi();
        FileConfiguration config = createTestConfigWithInvalidValues();
        // When
        param.load(config);
        // Then
        assertThat(param.isEnabled(), is(equalTo(true)));
        assertThat(param.isSecureOnly(), is(equalTo(false)));
        assertThat(param.isDisableKey(), is(equalTo(false)));
        assertThat(param.isIncErrorDetails(), is(equalTo(false)));
        assertThat(param.isAutofillKey(), is(equalTo(false)));
        assertThat(param.isEnableJSONP(), is(equalTo(false)));
        assertThat(param.getRealKey(), is(equalTo("")));
    }

    private static OptionsParamApi createOptionsParamApiWithConfig() {
        OptionsParamApi param = new OptionsParamApi();
        param.load(new ZapXmlConfiguration());
        return param;
    }

    private static FileConfiguration createTestConfig() {
        ZapXmlConfiguration config = new ZapXmlConfiguration();
        config.setProperty(API_ENABLED_KEY, "false");
        config.setProperty(API_SECURE_KEY, "true");
        config.setProperty(API_KEY_KEY, "ApiKey");
        config.setProperty(API_DISABLEKEY_KEY, "true");
        config.setProperty(API_INCERRORDETAILS_KEY, "true");
        config.setProperty(API_AUTOFILLKEY_KEY, "true");
        config.setProperty(API_ENABLEJSONP_KEY, "true");
        config.setProperty(API_NO_KEY_FOR_SAFE_OPS, "true");
        config.setProperty(API_REPORT_PERM_ERRORS, "true");
        return config;
    }

    private static FileConfiguration createTestConfigWithInvalidValues() {
        ZapXmlConfiguration config = new ZapXmlConfiguration();
        config.setProperty(API_ENABLED_KEY, "Not Boolean");
        config.setProperty(API_SECURE_KEY, "Not Boolean");
        config.setProperty(API_DISABLEKEY_KEY, "Not Boolean");
        config.setProperty(API_INCERRORDETAILS_KEY, "Not Boolean");
        config.setProperty(API_AUTOFILLKEY_KEY, "Not Boolean");
        config.setProperty(API_ENABLEJSONP_KEY, "Not Boolean");
        config.setProperty(API_NO_KEY_FOR_SAFE_OPS, "Not Boolean");
        config.setProperty(API_REPORT_PERM_ERRORS, "Not Boolean");
        return config;
    }

    private static class Configuration extends ZapXmlConfiguration {

        private static final long serialVersionUID = 3822957830178644758L;

        private boolean saved;

        @Override
        public void save() throws ConfigurationException {
            saved = true;
        }

        public boolean isSaved() {
            return saved;
        }
    }

}
