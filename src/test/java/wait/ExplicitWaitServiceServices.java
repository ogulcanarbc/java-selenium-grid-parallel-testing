package wait;

import helper.JavaScriptOperation;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ExplicitWaitServiceServices extends WaitService {

    protected Logger logger = Logger.getLogger(ExplicitWaitServiceServices.class);

    JavaScriptOperation javaScriptOperation;

    public ExplicitWaitServiceServices() {
        explicitWebDriverWait = new WebDriverWait(webdriver, 30);
    }

    public ExplicitWaitServiceServices(int timeOutSeconds) {
        explicitWebDriverWait = new WebDriverWait(webdriver, timeOutSeconds);
    }

    public ExplicitWaitServiceServices(int timeOutSeconds, int sleepInMs) {
        explicitWebDriverWait = new WebDriverWait(webdriver, timeOutSeconds,sleepInMs);
    }

    public WebElement waitVisibleOfElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return explicitWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public List<WebElement> waitVisibleOfAllElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return explicitWebDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public WebElement waitPresenceOfElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return explicitWebDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public List<WebElement> waitPresenceOfAllElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return explicitWebDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }


    public WebElement waitElementToBeClickableBy(By by) {
        try {
            logger.info("Expected to be clickable" + by.toString() + " element locator");
            return explicitWebDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not clickable! -> " + ex.getMessage());
            throw ex;
        }
    }

    public Boolean attributeToBeNotEmptyBy(WebElement webElement, String attribute) {
        try {
            explicitWebDriverWait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attribute));
            return true;
        } catch (AssertionError ex) {
            return false;
        }
    }

}


