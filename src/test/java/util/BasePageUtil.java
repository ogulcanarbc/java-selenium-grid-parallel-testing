package util;

import base.BaseTest;
import helper.JavaScriptOperation;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waitservices.WebDriverWaitServices;

import javax.swing.*;
import java.util.List;

public class BasePageUtil {
    protected Logger logger = Logger.getLogger(WebDriverWaitServices.class);
    JavaScriptOperation javaScriptOperation = new JavaScriptOperation();
    protected WebDriver webDriver;

    public BasePageUtil(WebDriver driver) {
        this.webDriver = driver;
    }

    protected WebElement findElement(By by) {
        try {
            logger.info("FindElement method called -> " + "findelement: " + by.toString() + " element");
            return new WebDriverWaitServices().waitVisibleOfElementLocatedBy(by);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    protected List<WebElement> findElements(By by) {
        try {
            logger.info("FindElements method called -> " + "findelement: " + by.toString() + " element");
            return new WebDriverWaitServices().waitPresenceOfAllElementLocatedBy(by);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    protected void click(By by) {
        try {
            logger.info("Click method called -> " + "clicking: " + by.toString() + " element");
            new WebDriverWaitServices().waitElementToBeClickableBy(by).click();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    protected void sendKey(By by, String text) {
        try {
            logger.info("SendKey method called -> " + "sendkey: " + by.toString() + " element");
            new WebDriverWaitServices().waitVisibleOfElementLocatedBy(by).sendKeys(text);
            logger.info("The text " + text + " was entered in");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    protected Boolean isExistElement(By by) {
        try {
            new WebDriverWaitServices().waitPresenceOfElementLocatedBy(by);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }

    protected void scrollToElementWithLocation(int elementLocationX, int elementLocationy) {
        String script = String.format("window.scrollTo(%d, %d);", elementLocationX, elementLocationy);
        javaScriptOperation.executeJS(script, true);
    }

    protected void scrollToWebElement(WebElement webElement) {
        String script = String.format("window.scrollTo(%d, %d);", webElement.getLocation().getX(), webElement.getLocation().getY());
        javaScriptOperation.executeJS(script, true);
    }
    protected void scrollToElementBy(By by) {
        WebElement webElement = findElement(by);
        scrollToElementWithLocation(webElement.getLocation().getX(), webElement.getLocation().getY());
        logger.info("Scrool to " + webElement.toString());
    }

    protected void moveToElement(By by) {
        Actions actions = new Actions(BaseTest.getDriver());
        actions.moveToElement(findElement(by)).build().perform();
        logger.info("Move to " + by.toString() + " element.");
    }


    protected void scrollToBottomPageWithJs(){
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        logger.info("Scrolled to the end of the page.");
    }

}
