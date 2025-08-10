package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String EMAIL_FOR_LOGIN = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String PASSWORD_FOR_LOGIN = System.getProperty("defaultPassword", ConfigProvider.configHiddenProperties.password());
    public final static String TEST_EMAIL_FOR_DISCOUNT_POPUP = "test_email@example.com";
    public final static String TEST_PHONE_NUMBER_FOR_DISCOUNT_POPUP = "6625639481";
    public final static String INPUT_TEXT_FOR_SEARCH = "gloves";
}
