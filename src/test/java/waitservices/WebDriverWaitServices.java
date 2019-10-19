package waitservices;

import helper.JavaScriptOperation;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import rule.TestListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverWaitServices extends Wait {
    protected Logger logger = Logger.getLogger(WebDriverWaitServices.class);

    JavaScriptOperation javaScriptOperation;
    public WebDriverWait webDriverWait;

    public WebDriverWaitServices() {

    }

    public WebDriverWaitServices(int timeOutSeconds) {
        webDriverWait = new WebDriverWait(waitDriver,timeOutSeconds);
    }

    public WebElement waitVisibleOfElementLocatedBy(By by){
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception ex){
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public List<WebElement> waitVisibleOfAllElementLocatedBy(By by){
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        }catch (Exception ex){
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public WebElement waitPresenceOfElementLocatedBy(By by){
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception ex){
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }

    public List<WebElement> waitPresenceOfAllElementLocatedBy(By by){
        try {
            logger.info("Waiting for " + by.toString() + " element locator");
            return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        }catch (Exception ex){
            logger.error(by + " elements can not find! -> " + ex.getMessage());
            throw ex;
        }
    }


    public WebElement waitElementToBeClickableBy(By by){
        try {
            logger.info("Expected to be clickable" + by.toString() + " element locator");
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception ex){
            logger.error(by + " elements can not clickable! -> " + ex.getMessage());
            throw ex;
        }
    }
}


