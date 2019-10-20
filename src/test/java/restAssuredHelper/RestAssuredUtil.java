package restAssuredHelper;

import base.BaseTest;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;


public class RestAssuredUtil extends BaseTest {

    Logger logger = Logger.getLogger(RestAssuredUtil.class);

    public static int getStatusCodeForGetRequest(String URL) {
        return RestAssured.get(URL).getStatusCode();
    }

    public static int getStatusCodeForPostRequest(String URL) {
        return RestAssured.post(URL).getStatusCode();
    }

    public static long getResponseTimeForGetRequest(String URL, TimeUnit timeUnitType) {
        return RestAssured.get(URL).getTimeIn(timeUnitType);
    }

    public static long getResponseTimeForPostRequest(String URL, TimeUnit timeUnitType) {
        return RestAssured.post(URL).getTimeIn(timeUnitType);
    }

    public static long getResponseTimeForGetRequest(String URL) {
        return RestAssured.get(URL).getTimeIn(TimeUnit.MILLISECONDS);
    }

    public static long getResponseTimeForPostRequest(String URL) {
        return RestAssured.post(URL).getTimeIn(TimeUnit.MILLISECONDS);
    }


}
