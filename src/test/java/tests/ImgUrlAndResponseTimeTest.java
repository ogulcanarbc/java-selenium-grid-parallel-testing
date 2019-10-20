package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;

public class ImgUrlAndResponseTimeTest extends BaseTest {

    /**
     * STEP - 2
     * TEST: Scroll edildiğinde gelen butik image linklerinin yüklenme sürelerin ve response kodları
     * bir csv dosysasına kaydedilmeli. (Bknz: ./reports/)
     */
    @Test
    public void imgUrlAndReponseTimeTest(){
        new HomePage(getDriver())
                .closePopUp()
                .writeBoutiqueImgSrcUrlAndResponseTimeToCsv();
    }
}
