package org.homePageTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class RedirectToMensPageTest extends BaseTest{

    @Test
    public void testRedirectToMansPage(){
        pageProvider.getHomePage().openHomePageAndCloseCookiesBanner()
                .getHeaderElements().clickOnButtonMenSection()
                .checkIsRedirectToMensPage()
                .getHeaderElements().checkAllHeaderElementsVisible();
    }


}
