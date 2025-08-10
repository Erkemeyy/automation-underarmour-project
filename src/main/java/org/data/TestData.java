package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String EMAIL_FOR_LOGIN = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String PASSWORD_FOR_LOGIN = System.getProperty("defaultPassword", ConfigProvider.configHiddenProperties.password());
    public final static String INPUT_TEXT_FOR_SEARCH = "gloves";
}
