package helper.ftp;

import helper.excel.ExcelProperties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import sun.net.ftp.FtpClient;

import java.io.*;

public class FtpHelper {

    private String userName;
    private String password;
    private String host;
    private FtpClient ftpClient;

    private FtpHelper() {
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public FTPClient createClient() throws IOException {
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        ftp.configure(config);
        ftp.connect(host);
        ftp.login(userName, password);
        return ftp;
    }

    public InputStream downloadFileAsInputStream(String filePath) {
        InputStream inputStream = null;
        FTPClient ftpClient;
        boolean b = false;
        String localFilePath = ExcelProperties.getInstance().getLocalFilePath();
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            ftpClient = createClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            b = ftpClient.retrieveFile(filePath, fos);
            inputStream = new FileInputStream(localFilePath);
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public boolean downloadFile(String remoteFilePath) {
        String localFilePath = ExcelProperties.getInstance().getLocalFilePath();
        FTPClient ftpClient;
        boolean isDownloaded = false;
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            ftpClient = createClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalActiveMode();
            isDownloaded = ftpClient.retrieveFile(remoteFilePath, fos);
            if (isDownloaded) {
                System.out.println("The file has been downloaded successfully.");
            } else {
                System.out.println("The file cannot be downloaded!");
            }
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isDownloaded;
    }

    public boolean uploadFileAsInputStream(String localFilePath) {
        FTPClient ftpClient;
        boolean isUploaded = false;
        try {
            ftpClient = createClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            File localFile = new File(localFilePath);
            String remoteFile = ExcelProperties.getInstance().getRemoteFilePath();
            InputStream inputStream = new FileInputStream(localFile);
            isUploaded = ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();
            if (isUploaded) {
                System.out.println("The file is uploaded successfully.");
            } else {
                System.out.println("The file cannot be uploaded!");
            }
            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isUploaded;
    }

    public static final class FtpUtilBuilder {

        private String userName;
        private String password;
        private String host;

        public FtpUtilBuilder() {
        }
        public static FtpUtilBuilder aFtpUtil() {
            return new FtpUtilBuilder();
        }

        public FtpUtilBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public FtpUtilBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public FtpUtilBuilder withHost(String host) {
            this.host = host;
            return this;
        }

        public FtpHelper build() {
            FtpHelper ftpHelper = new FtpHelper();
            ftpHelper.setUserName(userName);
            ftpHelper.setPassword(password);
            ftpHelper.setHost(host);
            return ftpHelper;
        }
    }
}
