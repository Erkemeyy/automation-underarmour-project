package org.productsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class AddProductToCartFromMensSwimwearPage extends BaseTest {
    @Test
    public void testProduct() {
        pageProvider.getHomePage().openHomePageAndCloseCookiesBanner()
                    .getHeaderElements().clickOnButtonMenSection()
                    .checkIsRedirectToMensPage()
                    .getHeaderElements().checkAllHeaderElementsVisible();
        pageProvider.getMensSwimwearPage().clickOnMensSwimwearPage()
                    .checkIsRedirectToMensSwimwearPage()
                    .clickOnFirstProductOnThePage()
                    .checkIsRedirectToProductDetailPage();

        ;
    }


}
