package org.elementsTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class DiscountPopUpOnMensPageApplyTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void testMensProductsCount() {
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
                .getHeaderElements().clickOnButtonMenSection()
                .applyToDiscountPopUpOnMensPage()
                .checkIsRedirectToMensPage();
    }

    @After
    public void backToHomePage(){
        logger.info("Post condition - log out from account");
        pageProvider.getMensPage()
                .getHeaderElements().clickOnHomePageIcon()
                .getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout()
                .checkIsRedirectToHomePage()
                .buttonLoginIsVisible();

    }
}
