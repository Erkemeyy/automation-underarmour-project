package org.LoginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

public class LoginTestWithTestCreds extends BaseTest {

    @Test
    public void testLoginWithTestCreds() {
        pageProvider.getHomePage()
                    .openHomePageAndCloseCookiesBanner()
                    .getHeaderElements().clickOnLoginButton();
        pageProvider.getLoginPage()
                    .loginWithCredentials(TestData.EMAIL_FOR_LOGIN, TestData.PASSWORD_FOR_LOGIN);
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                    .buttonMyAccountIsVisible()
                ;
    }
}
