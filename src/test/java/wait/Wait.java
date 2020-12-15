package wait;

import base.BaseTest;
import org.openqa.selenium.WebDriver;

public class Wait {
    /*
    should not be used unless needed.
     */
    public static void forceSecondWait(int x) {
        try {
            Thread.sleep(x * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected WebDriver webdriver = BaseTest.getDriver();
}
