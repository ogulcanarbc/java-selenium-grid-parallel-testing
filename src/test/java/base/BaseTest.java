package base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import helper.date.DateAndTimeHelper;
import helper.file.FileHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import waitservices.WebDriverWaitServices;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    protected static WebDriverWaitServices webdriver;
    protected CapabilityFactory capabilityFactory = new CapabilityFactory();
    public static String caseNo;
    public static String browserName;
    public String localPath = System.getProperty("user.dir");
    public static String url = "https://www.trendyol.com/";

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
                    .getFile("src/test/reports/screenshots/" + browserName + "/"
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
         iTestContext.getFailedTests().getAllResults().stream().forEach(System.out::println);
        driver.remove();
    }
}