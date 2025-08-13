package org.homePageTest;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class VerifyCurrentCollectionAndGoToShopNowPage extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void testHaloPage(){
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
                .checkIsRedirectToHomePage()
                .checkNewCollectionElementsIsVisible()
                .clickOnShopNowButton()
                .checkIsRedirecktToHaloPage()
                ;
    }

    @After
    public void backToHomePage(){
        logger.info("Post condition - log out from account");
        pageProvider.getHaloPage()
                    .getHeaderElements().clickOnHomePageIcon()
                    .getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout()
                    .checkIsRedirectToHomePage()
                    .buttonLoginIsVisible();

    }
}
