package page;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import util.BasePageUtil;
import waitservices.Wait;

public class LoginPage extends BasePageUtil {

    Logger logger = Logger.getLogger(LoginPage.class);

    private final By signInContainer = By.className("login-register-button-container");
    private final By txtUserName = By.id("email");
    private final By txtPassword = By.id("password");
    private final By buttonLogin = By.id("loginSubmit");
    private final By errorBox = By.id("errorBox");
    private final By myAccountArea = By.id("logged-in-container");

    private String expectedInvalidMailOrPasswordMessage = "Hatalı E-Posta / Şifre. Tekrar Deneyin.";
    private String expextedInvalidPasswordMessage = "Lütfen şifre giriniz.";
    private String expetedInvalidMailMessage = "Lütfen email adresinizi giriniz.";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(String email, String password) {
        //Wait.forceSecondWait(2);
        sendKey(txtUserName, email);
        sendKey(txtPassword, password);
        return this;
    }

    public MainPage clickLoginButton(String caseNo) {
        click(buttonLogin);
        //  Wait.forceSecondWait(3);
        logger.info("Click Login Button");
        loginControl(caseNo);
        return new MainPage(webDriver);
    }

    public void loginControl(String caseNo) {

        switch (caseNo) {
            case "Case1":
                successLoginControl();
                break;
            case "Case5":
            case "Case6":
            case "Case2":
                IncorrectEmailOrPasswordControl();
                break;
            case "Case3":
                blankOrInvalidPasswordControl();
                break;
            case "Case4":
            case "Case7":
                blankOrInvalidEmailControl();
                break;
            default:
                break;
        }

    }

    private void successLoginControl() {

        Assert.assertTrue(isExistElement(myAccountArea), "-> " + BaseTest.caseNo + "Hesabım Alanı Gözükmüyor, Başarılı Login Olunamamış! ");
        logger.info("Login is Successful.");
    }

    private void IncorrectEmailOrPasswordControl() {

        Assert.assertTrue(expectedInvalidMailOrPasswordMessage.equalsIgnoreCase(findElement(errorBox).getText()), "Yanlış Hata Mesajı!. " +
                BaseTest.caseNo + " -> Beklenen Mesaj: " + expectedInvalidMailOrPasswordMessage + " , Gelen Mesaj: " + findElement(errorBox).getText());
    }

    private void blankOrInvalidPasswordControl() {
        Assert.assertTrue(expextedInvalidPasswordMessage.equalsIgnoreCase(findElement(errorBox).getText()), "Yanlış Hata Mesajı!. " +
                BaseTest.caseNo + " -> Beklenen Mesaj: " + expextedInvalidPasswordMessage
                + ",Gelen Mesaj: " + findElement(errorBox).getText());
    }

    private void blankOrInvalidEmailControl() {
        Assert.assertTrue(expetedInvalidMailMessage.equalsIgnoreCase(findElement(errorBox).getText()), "Yanlış Hata Mesajı!. " +
                BaseTest.caseNo + " -> Beklenen Mesaj: " + expetedInvalidMailMessage + ",Gelen Mesaj: " + findElement(errorBox).getText());
    }


}

