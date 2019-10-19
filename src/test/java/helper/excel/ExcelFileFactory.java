package helper.excel;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

public class ExcelFileFactory {

  private ExcelFileFactory() {
  }

  public static ExcelFile createExcelFile(ExcelFileType excelFileType) {
    ExcelFile excelFile;
    if (excelFileType == ExcelFileType.LOCAL) {
      excelFile = new LocalExcelFile();
    } else if (excelFileType == ExcelFileType.FTP) {
      excelFile = new FtpExcelFile();
    } else {
      throw new InvalidOperationException("Excel file type is not found");
    }
    return excelFile;
  }

}
