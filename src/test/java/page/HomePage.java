package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import util.BasePageUtil;
import waitservices.Wait;

public class HomePage extends BasePageUtil {

    Logger logger = Logger.getLogger(HomePage.class);
    public static final By homePagePopUp = By.xpath("//a[@title='Close']");
    private final By signInIcon = By.xpath("//i[@class='icon navigation-icon-user']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage closePopUp() {
        if (isExistElement(homePagePopUp)) {
            findElement(homePagePopUp).click();
        }else
        {
            logger.error("Home Page PopUp Not Found.");
        }
        logger.info("Home page popup closed.");
        return this;
    }

    public LoginPage clickSignInIconButton() {
       // Wait.forceSecondWait(2);
        click(signInIcon);
        logger.info("Click Sign In Icon Button..");
        return new LoginPage(webDriver);
    }
}
