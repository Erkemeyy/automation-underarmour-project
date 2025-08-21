package org.elementsTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class DiscountPopUpOnMensPageApplyTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void testDiscountPopUp() {
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
                .getHeaderElements().clickOnButtonMenSection()
                .applyToDiscountPopUpOnMensPage()
                .checkIsRedirectToMensPage();
    }

    @After
    public void backToHomePage(){
        logger.info("Back to home page");
        pageProvider.getMensPage()
                .getHeaderElements().clickOnHomePageIcon()
                .getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout()
                .checkIsRedirectToHomePage()
                .buttonLoginIsVisible();

    }
}
