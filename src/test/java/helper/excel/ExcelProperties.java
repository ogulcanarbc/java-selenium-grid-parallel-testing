package helper.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ExcelProperties {

    private static ExcelProperties instance = null;
    private final Properties properties = new Properties();

    private ExcelProperties() {
        try {
            InputStream input = new FileInputStream(ClassLoader.getSystemResource("properties/excel.properties").getPath());
            this.properties.load(input);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static ExcelProperties getInstance() {
        if (instance == null) {
            instance = new ExcelProperties();
        }
        return instance;
    }

    public String getFilePath() {
        return this.properties.getProperty("filePath");
    }

    public String getSheetName() {
        return this.properties.getProperty("sheetname");
    }

    public String getFileName() {
        return this.properties.getProperty("name");
    }

    public String getLocalFilePath() {
        return this.properties.getProperty("localFilePath");
    }

    public String getRemoteFilePath() {
        return this.properties.getProperty("remoteFilePath");
    }

    public void setLocalFilePath(String localPath) {
        this.properties.setProperty("localFilePath", localPath);
    }

    public void setRemoteFilePath(String remotePath) {
        this.properties.setProperty("remoteFilePath", remotePath);
    }

    /**
     * @implNote sourceType must be LOCAL or FTP
     * @return
     */
    public ExcelFileType getExcelSourceType() {
        return ExcelFileType.valueOf(this.properties.getProperty("sourceType"));
    }


}
