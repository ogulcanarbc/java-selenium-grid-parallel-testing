package dataprovider;

import helper.excel.ExcelFile;
import helper.excel.ExcelFileFactory;
import helper.excel.ExcelProperties;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static helper.excel.ExcelHelper.*;

public enum LoginDataReader {
    INSTANCE;
    //Columns
    private final String CASE_NAME = "A";
    private final String USERNAME = "B";
    private final String PASSWORD = "C";

    static Logger logger = (Logger) Logger.getInstance(LoginDataReader.class);

    // TODO object pooling and iterable add
    public List<LoginDataModel> readExcelData() {
        Workbook workbook;
        ExcelFile excelFile = ExcelFileFactory.createExcelFile(ExcelProperties.getInstance().getExcelSourceType());
        if (ExcelProperties.getInstance().getExcelSourceType().toString().equalsIgnoreCase("LOCAL")) {
            workbook = openWorkbook(excelFile.readExcelFile(ExcelProperties.getInstance().getLocalFilePath()));

        } else {
            workbook = openWorkbook(excelFile.readExcelFile(ExcelProperties.getInstance().getRemoteFilePath()));
        }
        List<LoginDataModel> loginDataList = new ArrayList<>();
        //TODO sheet name'i properties den al
        loginDataList.addAll(createLoginDateList(workbook, getSheetByName(workbook,"logindata")));

        return loginDataList;
    }

    private List<LoginDataModel> createLoginDateList(Workbook workbook, Sheet sheet) {
        List<LoginDataModel> flightDataList = new ArrayList<>();
        Row row;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null || isEmptyRow(row)) {
                break;
            }
            LoginDataModel loginData = createLoginDate(row);
            flightDataList.add(loginData);
        }
        close(workbook);
        return flightDataList;
    }


    private void close(Workbook workbook) {
        try {
            closeWorkbook(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected LoginDataModel createLoginDate(Row row) {  //TODO düzeldilecek
        return new LoginDataBuilder()
                .withCaseName(getRowCellValueAsString(row,CASE_NAME))
                .withUsername(getRowCellValueAsString(row, USERNAME))
                .withPassword(getRowCellValueAsString(row, PASSWORD))
                .build();

    }

}
