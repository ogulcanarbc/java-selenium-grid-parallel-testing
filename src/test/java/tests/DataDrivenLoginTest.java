package tests;

import dataprovider.DataProvider;
import dataprovider.LoginDataModel;
import org.apache.log4j.Logger;
import page.HomePage;
import rule.TestListener;
import base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class DataDrivenLoginTest extends BaseTest {
    Logger logger = Logger.getLogger(DataDrivenLoginTest.class);

    /**
     * STEP-1
     * TEST: Kendi tasarlayacağın login caselerini, data driven bir şekilde koşmanı bekliyoruz.
     * <p>
     * <p>
     * CaseNo	Steps	Expected Result
     * Case1	Doğru Kullanıcı adı ve Şifre girilir.	Login olunduğu görülür
     * Case2	Yanlılş Kullanıcı adı ve Şifre girilir.	"Hatalı E-Posta / Şifre. Tekrar Deneyin." mesajı geldiği görülür
     * Case3	Doğru Kullanıcı ad ve Boş şifre girilir.	"Lütfen şifre giriniz." mesajının geldiği görülür
     * Case4	Boş Kullanıcı adı ve Doğru şifre girilir.	"Lütfen email adresinizi giriniz." mesajının geldiği görülür
     *
     * @param loginData
     * @throws InterruptedException
     */

    @Test(dataProvider = "logindata", dataProviderClass = DataProvider.class)
    public void loginTest(LoginDataModel loginData) throws InterruptedException {
        BaseTest.caseNo = loginData.getCaseNo();

        new HomePage(getDriver())
                .closePopUp()
                .clickSignInIconButton()
                .login(loginData.getUsername(), loginData.getPassword())
                .clickLoginButton(loginData.getCaseNo());
    }
}