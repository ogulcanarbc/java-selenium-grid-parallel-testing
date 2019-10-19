package helper.ftp;

import helper.excel.ExcelFileType;
import helper.excel.ExcelProperties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class FtpProperties {

    private static FtpProperties instance = null;
    private final Properties properties = new Properties();

    private FtpProperties() {
        try {
            InputStream input = new FileInputStream(ClassLoader.getSystemResource("properties/ftp.properties").getPath());
            this.properties.load(input);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static FtpProperties getInstance() {
        if (instance == null) {
            instance = new FtpProperties();
        }
        return instance;
    }

    public String getHostName() {
        return this.properties.getProperty("HOST");
    }

    public String getUsername() {
        return this.properties.getProperty("USERNAME");
    }

    public String getPassword() {
        return this.properties.getProperty("PASSWORD");
    }


}
