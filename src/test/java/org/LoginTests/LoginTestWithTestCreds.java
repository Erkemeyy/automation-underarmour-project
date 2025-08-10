package org.LoginTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.After;
import org.junit.Test;

public class LoginTestWithTestCreds extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

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

    @After
    public void logOut(){
        logger.info("Post condition - log out from account");
        pageProvider.getHomePage()
                    .logOutFromAccount()
                    .getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout()
                    .checkIsRedirectToHomePage()
                    .buttonLoginIsVisible();

    }
}
