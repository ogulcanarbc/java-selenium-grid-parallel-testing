package helper.excel;

import helper.ftp.FtpHelper;
import helper.ftp.FtpProperties;

import java.io.InputStream;

public class FtpExcelFile implements ExcelFile {

    @Override
    public InputStream readExcelFile(String path) {
        getFtpConfig().downloadFile(path);
        LocalExcelFile localExcelFile = new LocalExcelFile();
        return localExcelFile.readExcelFile(ExcelProperties.getInstance().getFilePath());
    }

    @Override
    public boolean writeExcelFile(String path) {
        return getFtpConfig().uploadFileAsInputStream(path);
    }

    private FtpHelper getFtpConfig() {
        return new FtpHelper.FtpUtilBuilder()
                .withHost(FtpProperties.getInstance().getHostName())
                .withUserName(FtpProperties.getInstance().getUsername())
                .withPassword(FtpProperties.getInstance().getPassword())
                .build();
    }
}
