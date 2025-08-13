package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class ParentPage extends CommonActionsWithElements {
    protected String baseURL = ConfigProvider.configProperties.base_url();
    protected String loginURL = ConfigProvider.configProperties.login_url();
    public ParentPage(WebDriver webDriver) {
       super(webDriver);
    }

    abstract protected String getRelativeURL();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
                , baseURL + getRelativeURL()
                , webDriver.getCurrentUrl()
        );
    }

    protected void checkUrlWithParam(int currentPage) {
        Assert.assertEquals("URL is not expected"
                , baseURL + getRelativeURL() + "?page=" + currentPage
                , webDriver.getCurrentUrl()
        );
    }

}