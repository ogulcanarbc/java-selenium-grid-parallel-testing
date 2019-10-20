package tests;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import page.HomePage;

public class BoutiqueUrlAndReponseCodeTest extends BaseTest {
    Logger logger = Logger.getLogger(DataDrivenLoginTest.class);

    @Test
    public void boutiqueUrlAndReponseCodeTest() {
        new HomePage(BaseTest.getDriver())
                .closePopUp()
                .writeBoutiqueUrlAndResponseCodeToCsv();
    }

}
