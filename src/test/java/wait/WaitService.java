package wait;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitService {

    protected WebDriver webdriver = BaseTest.getDriver();
    public Wait<WebDriver> fluentWebDriverWait;
    public WebDriverWait explicitWebDriverWait;

    /**
     * should not be used unless needed.
     * @param second
     */
    public static void threadWait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
