package waitservices;

import helper.JavaScriptOperation;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverWaitServices extends Wait {
    protected Logger logger = Logger.getLogger(WebDriverWaitServices.class);

    JavaScriptOperation javaScriptOperation;
    public WebDriverWait wait;

    public WebDriverWaitServices() {
        wait = new WebDriverWait(webdriver, 30);
    }

    public WebDriverWaitServices(int timeOutSeconds) {
        wait = new WebDriverWait(webdriver, timeOutSeconds);
    }

    public WebElement waitVisibleOfElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public List<WebElement> waitVisibleOfAllElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public WebElement waitPresenceOfElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public List<WebElement> waitPresenceOfAllElementLocatedBy(By by) {
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }


    public WebElement waitElementToBeClickableBy(By by) {
        try {
            logger.info("Expected to be clickable" + by.toString() + " element locator");
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (AssertionError ex) {
            logger.error(by + " elements can not clickable! -> " + ex.getMessage());
            throw ex;
        }
    }

    public Boolean attributeToBeNotEmptyBy(WebElement webElement, String attribute) {
        try {
            wait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attribute));
            return true;
        } catch (AssertionError ex) {
            return false;
        }
    }


}


