import dataprovider.DataProvider;
import dataprovider.LoginDataModel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import rule.TestListener;
import base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    Logger logger = Logger.getLogger(LoginTest.class);

    @Test(dataProvider = "logindata",dataProviderClass = DataProvider.class)
    public void loginTest(LoginDataModel loginData) throws InterruptedException {

        caseName=loginData.getCasename();
        logger.info("DATA NO:" + loginData.getCasename());
        logger.info("PASSWORD : " + loginData.getPassword());
        logger.info("USERNAME: " + loginData.getUsername());
        getDriver().navigate().to("https://eksisozluk.com/");
        Assert.fail("FAILLL");
    }

}