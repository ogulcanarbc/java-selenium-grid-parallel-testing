package page;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {

    Logger logger = Logger.getLogger(LoginPage.class);

    private static final By txtUserName = By.id("email");
    private static final By txtPassword = By.id("password");
    private static final By buttonLogin = By.id("loginSubmit");
    private static final By errorBox = By.id("errorBox");
    private static final By myAccountArea = By.id("logged-in-container");

    private String expectedInvalidMailOrPasswordMessage = "Hatalı E-Posta / Şifre. Tekrar Deneyin.";
    private String expextedInvalidPasswordMessage = "Lütfen şifre giriniz.";
    private String expetedInvalidMailMessage = "Lütfen email adresinizi giriniz.";


    public LoginPage login(String email, String password) {
        sendKey(txtUserName, email);
        sendKey(txtPassword, password);
        return this;
    }

    public MainPage clickLoginButton(String dataNo) {
        click(buttonLogin);
        logger.info("Click Login Button");
        loginControl(dataNo);
        return new MainPage();
    }

    /**
     * 4 adet case'i 7 data ile çokladıktan sonra yapılan kontrol.
     * @param caseNo
     */
    public void loginControl(String caseNo) {

        switch (caseNo) {
            case "Data1":
                successLoginControl(); //Case 1
                break;
            case "Data5":
            case "Data6":
            case "Data2":
                IncorrectEmailOrPasswordControl(); // Case 2
                break;
            case "Data3":
                blankOrInvalidPasswordControl(); // Case 3
                break;
            case "Data4":
            case "Data7":
                blankOrInvalidEmailControl(); // Case 4
                break;
            default:
                break;
        }

    }

    /**
     * @apiNote Testler paralel ve aynı anda koştuğıu için
     */
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

