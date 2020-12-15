package base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import groovy.util.logging.Slf4j;
import helper.date.DateAndTimeHelper;
import helper.file.FileHelper;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import rule.TestListener;


@Listeners(TestListener.class)
@Slf4j
public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    protected CapabilityFactory capabilityFactory = new CapabilityFactory();
    public static String caseNo;
    public static String browserName;
    public static String localPath = System.getProperty("user.dir");
    public static String url = "https://www.trendyol.com/";
    public static RequestSpecification request;

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setup(String browser) throws MalformedURLException {
        PropertyConfigurator.configure(localPath + "/src/test/resources/properties/log4j.properties");
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get().navigate().to(url);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult, ITestContext iTestContext) {
        browserName = capabilityFactory.getCapabilities("browserName").getBrowserName();
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File screenShotName;
        if (!iTestResult.isSuccess()) {
            screenShotName = FileHelper.getInstance()
                    .getFile(localPath + "/reports/screenshots/" + browserName + "/"
                            + DateAndTimeHelper.getNowDateDayMonthYearFormatAsString() + "/"
                            + iTestResult.getName() + caseNo + "-"
                            + DateAndTimeHelper.getNowDateDHoursMinuteSecondFormatAsString() + ".png");
            try {
                FileUtils.copyFile(scrFile, screenShotName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        getDriver().quit();
    }

    @AfterClass
    void terminateDriver(ITestContext iTestContext) {
        iTestContext.getFailedTests().getAllResults().forEach(System.out::println);
        driver.remove();
    }
}