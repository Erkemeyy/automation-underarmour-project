package org.productsTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class AddProductToCartFromMensSwimwearPage extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void testProduct() {
        pageProvider.getHomePage().openHomePageAndCloseCookiesBanner()
                    .getHeaderElements().clickOnButtonMenSection()
                    .checkIsRedirectToMensPage()
                    .getHeaderElements().checkAllHeaderElementsVisible();
        pageProvider.getMensSwimwearPage().clickOnMensSwimwearPage()
                    .checkIsRedirectToMensSwimwearPage()
                    .clickOnFirstProductOnThePage()
                    .checkIsRedirectToProductDetailPage()
                    .clickOnAddToBagButton()
                    .getAddToBagPopUp().checkIsAddToBagPopUpAppeared()
                    .clickOnViewBagButton()
                    .checkIsCartPageAndThereIsOneProduct()
                    .getHeaderElements().checkAllHeaderElementsVisible();
        ;
    }

    @After
    public void backToHomePage(){
        logger.info("Post condition - log out from account");
        pageProvider.getCartPage()
                .getHeaderElements().clickOnHomePageIcon()
                .getDiscountPopUp().waitAndCloseDiscountPopUpAfterLogout()
                .checkIsRedirectToHomePage()
                .buttonLoginIsVisible();

    }


}
