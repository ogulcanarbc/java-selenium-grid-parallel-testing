package tests;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import page.HomePage;

public class LinkAndLinkResponseCodeTest extends BaseTest {
    Logger logger = Logger.getLogger(DataDrivenLoginTest.class);

    /**
     * STEP-1
     * Test: Trendyol ana sayfasındaki tüm butik linkleri ve bu linklere request atıldığında dönen
     * response kodları bir csv dosyasına kaydedilmeli. (Bknz: ./reports/)
     */

    @Test
    public void urlAndReponseCodeTest() {
        new HomePage(getDriver())
                .closePopUp()
                .writeBoutiqueUrlAndResponseCodeToCsv();
    }
}
