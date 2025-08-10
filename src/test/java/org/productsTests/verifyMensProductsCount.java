package org.productsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class verifyMensProductsCount extends BaseTest {
    @Test
    public void testMensProductsCount() {
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
                .getHeaderElements().clickOnButtonMenSection()
                .checkIsRedirectToMensPage()
                .applyToDiscountPopUpOnMensPage();
    }
}
