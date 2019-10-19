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
public class LoginTest extends BaseTest {

    Logger logger = Logger.getLogger(LoginTest.class);

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