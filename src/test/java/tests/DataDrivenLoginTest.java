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
     *
     * @param loginData
     * @throws InterruptedException
     */

    @Test(dataProvider = "logindata", dataProviderClass = DataProvider.class)
    public void loginTest(LoginDataModel loginData) throws InterruptedException {
        BaseTest.caseNo = loginData.getCaseNo();

        new HomePage(BaseTest.getDriver())
                .closePopUp()
                .clickSignInIconButton()
                .login(loginData.getUsername(), loginData.getPassword())
                .clickLoginButton(loginData.getCaseNo());
    }
}