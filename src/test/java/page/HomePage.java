package page;

import base.BaseTest;
import helper.date.DateAndTimeHelper;
import helper.file.FileHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import restAssuredConfig.RestConfig;
import util.BasePageUtil;
import waitservices.Wait;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends BasePageUtil {

    Logger logger = Logger.getLogger(HomePage.class);

    public static final By homePagePopUp = By.xpath("//a[@title='Close']");
    private final By signInIcon = By.xpath("//i[@class='icon navigation-icon-user']");
    private final By boutiqueLinkUri = By.xpath("//*[@class='category-header']");
    HashMap<String, String> urlAndRespCode = new HashMap<String, String>();
    RestConfig restConfig;
    String href;
    String statusCode;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage closePopUp() {
        if (isExistElement(homePagePopUp)) {
            findElement(homePagePopUp).click();
        } else {
            logger.error("Home Page PopUp Not Found.");
        }
        logger.info("Home page popup closed.");
        return this;
    }

    public LoginPage clickSignInIconButton() {
        // Wait.forceSecondWait(2);
        click(signInIcon);
        logger.info("Click Sign In Icon Button..");
        return new LoginPage(webDriver);
    }

    public HashMap<String, String> getBoutiqueUrlAndResponseCodeAfterSet() {
        Wait.forceSecondWait(4);
        List<WebElement> elements = findElements(boutiqueLinkUri);
        for (int i = 0; i < elements.size(); i++) {
            href = elements.get(i).getAttribute("href");
            statusCode = String.valueOf(restConfig.getStatusCode(href));
            urlAndRespCode.put(href, statusCode);
        }
        return urlAndRespCode;
    }

    public void writeBoutiqueUrlAndResponseCode() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(forBoutiqueUrlAndResponseCodeCsvFile()))) {
            writer.write(convertForCsvDataFromHashMapToStringBuilderType().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Boutique url and response codes saved to csv file successfully.");
    }

    public File forBoutiqueUrlAndResponseCodeCsvFile() {
        return FileHelper.getInstance().getFile(BaseTest.localPath + "/reports/boutiqueLinkAndResponseCode/"
                + DateAndTimeHelper.getNowDateDayMonthYearFormatAsString() + "/"
                + DateAndTimeHelper.getNowDateDHoursMinuteSecondFormatAsString()
                + ".csv");
    }

    public StringBuilder convertForCsvDataFromHashMapToStringBuilderType() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("URL")
                .append(",")
                .append("StatusCode")
                .append("\n");
        for (Map.Entry<String, String> entry : getBoutiqueUrlAndResponseCodeAfterSet().entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(',')
                    .append(entry.getValue())
                    .append("\n");
        }
        return stringBuilder;
    }
}