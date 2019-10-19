package helper.excel;

import java.io.InputStream;

public interface ExcelFile {

    InputStream readExcelFile(String path);

    boolean writeExcelFile(String path);
}
