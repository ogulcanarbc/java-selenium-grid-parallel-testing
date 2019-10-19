package waitservices;


import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FluentWaitServices extends Wait {

    org.openqa.selenium.support.ui.Wait fluentWait;

    FluentWaitServices() {

    }

    FluentWaitServices(int timeOutSeconds, int poolingEverySeconds) {
        fluentWait = new FluentWait<>(waitDriver)
                .withTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .pollingEvery(poolingEverySeconds, TimeUnit.SECONDS);
    }

    FluentWaitServices(int timeOutSeconds, int poolingEverySeconds, Exception ignoreException) {
        fluentWait = new FluentWait<>(waitDriver)
                .withTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .pollingEvery(poolingEverySeconds, TimeUnit.SECONDS)
                .ignoring(ignoreException.getClass());
    }


}
