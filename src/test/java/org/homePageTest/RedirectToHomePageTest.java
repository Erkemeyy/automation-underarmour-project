package org.homePageTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class RedirectToHomePageTest extends BaseTest{

    @Test
    public void testRedirectToMansPage(){
        pageProvider.getHomePage().openHomePageAndCloseCookiesBanner()
                .getHeaderElements().clickOnButtonMenSection()
                .checkIsRedirectToMensPage()
                .getHeaderElements().checkAllHeaderElementsVisible();
    }


}
