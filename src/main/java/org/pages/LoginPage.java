package org.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.utils.Utils_Custom.waitABit;

public class LoginPage extends ParentPage {
    private Logger logger = LogManager.getLogger(getClass());

    @FindBy(xpath = "//input[@autocomplete='username']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//input[@autocomplete='current-password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonLogin;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "oauth2/default/v1/authorize";
    }

    public LoginPage loginWithCredentials(String emailForLogin, String passwordForLogin) {
        checkUrlForLoginPage();
        enterTextIntoInputEmailAddress(emailForLogin);
        enterTextIntoInputPassword(passwordForLogin);
        clickOnElement(buttonLogin);
        waitABit(10);
        return this;
    }

    private LoginPage enterTextIntoInputEmailAddress(String emailForLogin) {
        clearAndEnterTextToElement(inputEmailAddress, emailForLogin);
        return this;
    }

    private LoginPage enterTextIntoInputPassword(String passwordForLogin) {
        clearAndEnterTextToElement(inputPassword, passwordForLogin);
        return this;
    }

    protected void checkUrlForLoginPage() {
        String url = webDriver.getCurrentUrl();
        logger.info("Checking login page URL: " + url);
        try {
            URI uri = new URI(url);
            Assert.assertEquals("Host/path mismatch",
                    "/oauth2/default/v1/authorize", uri.getPath());
            logger.info("Path is correct: " + uri.getPath());

            // Розпарсити query у мапу
            Map<String, String> q = Arrays.stream(uri.getQuery().split("&"))
                    .map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(a -> a[0], a -> a.length > 1 ? a[1] : ""));

            // Обов’язкові параметри
            Assert.assertTrue("Missing client_id", q.containsKey("client_id"));
            logger.info("client_id found: " + q.get("client_id"));

            Assert.assertTrue(q.containsKey("code_challenge"));
            logger.info("code_challenge found: " + q.get("code_challenge"));

            Assert.assertTrue("Missing client_id", q.containsKey("client_id"));
            logger.info("client_id found: " + q.get("client_id"));

            Assert.assertTrue("Missing code_challenge", q.containsKey("code_challenge"));
            logger.info("code_challenge found: " + q.get("code_challenge"));

            Assert.assertEquals("S256", q.getOrDefault("code_challenge_method", ""));
            logger.info("code_challenge_method is S256");

            Assert.assertEquals("en-us", q.getOrDefault("locale", ""));
            logger.info("locale is en-us");

            Assert.assertEquals("code", q.getOrDefault("response_type", ""));
            logger.info("response_type is code");

            Assert.assertEquals("openid+offline_access", q.getOrDefault("scope", ""));
            logger.info("scope is openid+offline_access");

            Assert.assertTrue("Missing state", q.containsKey("state"));
            logger.info("state found: " + q.get("state"));

            // redirect_uri перевірити після декодування
            String redirect = URLDecoder.decode(q.getOrDefault("redirect_uri",""), StandardCharsets.UTF_8);
            Assert.assertEquals("https://www.underarmour.com/api/auth/v2/login/", redirect);

        } catch (Exception e) {
            Assert.fail("Invalid URL format: " + e.getMessage());
        }
    }
}

