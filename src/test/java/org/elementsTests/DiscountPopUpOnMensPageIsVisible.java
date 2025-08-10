package org.elementsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class DiscountPopUpOnMensPageIsVisible extends BaseTest {
    @Test
    public void testMensProductsCount() {
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
                .getHeaderElements().clickOnButtonMenSection()
                .checkIsRedirectToMensPage()
                .verifyDiscountPopUpOnMensPage();
    }
}
