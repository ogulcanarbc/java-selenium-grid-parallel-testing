package dataprovider;

import org.apache.log4j.Logger;

import java.util.List;

public class DataProvider {

    private static Logger logger = Logger.getLogger(DataProvider.class);
    public static List<LoginDataModel> loginData;

    /**
     * Excel'den dataları okuyarak, dataprovider'ın anlayacağı 2 boyulu object dizisine çevirir
     *ve dataprovider'a parametre olarak verir
     *
     * @return
     * @apiNote For Data Driven Testing
     */

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

    private static Object[][] convertLoginDataToObject2d(List<LoginDataModel> loginDataList) {
        return loginDataList
                .stream()
                .map(p -> new LoginDataModel[]{p})
                .toArray(Object[][]::new);
    }
}
