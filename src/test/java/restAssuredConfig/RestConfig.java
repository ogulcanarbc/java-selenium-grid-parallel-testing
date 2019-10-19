package restAssuredConfig;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.BasePageUtil;

import java.util.List;


public class RestConfig extends BaseTest {

    Logger logger = Logger.getLogger(RestConfig.class);
    private static final By boutiqueLinkUri = By.xpath("//*[@class='category-header']");

    public static int getStatusCode(String URL) {
      return RestAssured.get(URL).getStatusCode();
    }
}
