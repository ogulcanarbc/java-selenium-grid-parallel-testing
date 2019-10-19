package helper.excel;

import helper.file.FileHelper;

import javax.annotation.Nullable;
import java.io.InputStream;

public class LocalExcelFile implements ExcelFile {

  @Nullable
  @Override
  public InputStream readExcelFile(String path) {
    InputStream inputStream = null;
    try {
      inputStream = FileHelper.getInstance().getFileStream(path, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return inputStream;
  }

/*  @Override
  public boolean writeExcelFile(String path) {
    InputStream inputStream = null;
    try {
      inputStream = FileHelper.getInstance().getFileStream(path, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }*/

}
