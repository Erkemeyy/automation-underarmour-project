package org.productsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class findSpecificProductTest extends BaseTest {

    @Test
    public void testSearchSpecificProduct(){
        pageProvider.getHomePage()
                .openHomePageAndCloseCookiesBanner()
        ;
    }
}
