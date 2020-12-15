package wait;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class FluentWaitServices extends WaitService {

    FluentWaitServices() {
        fluentWebDriverWait = new FluentWait<WebDriver>(webdriver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
    }

    FluentWaitServices(int timeOutSeconds, int poolingEverySeconds) {
        fluentWebDriverWait = new FluentWait<WebDriver>(webdriver)
                .withTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .pollingEvery(poolingEverySeconds, TimeUnit.SECONDS);
    }

    FluentWaitServices(int timeOutSeconds, int poolingEverySeconds, Exception ignoreException) {
        fluentWebDriverWait = new FluentWait<WebDriver>(webdriver)
                .withTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .pollingEvery(poolingEverySeconds, TimeUnit.SECONDS)
                .ignoring(ignoreException.getClass());
    }

}
