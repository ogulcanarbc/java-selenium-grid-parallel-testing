package dataprovider;

import org.apache.log4j.Logger;

import java.util.List;

public class DataProvider {

    private static Logger logger = Logger.getLogger(DataProvider.class);
    public static List<LoginDataModel> loginData;

    @org.testng.annotations.DataProvider(name = "logindata")
    public Object[][] dataProviderMethod() {
        return getLoginData();
    }

    private static Object[][] getLoginData() {
        return convertLoginDataToObject2d(getLoginDataList());
    }

    private static List<LoginDataModel> getLoginDataList() {
        return LoginDataReader.INSTANCE.readExcelData();
    }

    private static Object[][] convertLoginDataToObject2d(List<LoginDataModel> logindatalist) {
        return logindatalist
                .stream()
                .map(p -> new LoginDataModel[]{p})
                .toArray(Object[][]::new);
    }
}
