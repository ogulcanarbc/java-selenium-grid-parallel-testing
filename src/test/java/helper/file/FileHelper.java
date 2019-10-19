package helper.file;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileHelper {

    private static FileHelper instance;
    private static Object lock = new Object();
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final String YAML = ".yaml";
    private static final String JSON = ".json";
    private static final String XML = ".xml";
    private static final String PROP = ".properties";

    private FileHelper() {
    }

    public static FileHelper getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new FileHelper();
            }
        }
        return instance;
    }

    public File getFile(String filePath) {
        return new File(filePath);
    }

    public InputStream getFileStream(String filePath) throws Exception {
        return getFileStream(filePath, true);
    }

    public InputStream getFileStream(String filePath, boolean classPath) throws Exception {
        try {
            return classPath ? getClass().getClassLoader().getResourceAsStream(filePath)
                    : new FileInputStream(filePath);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static File toFile(String path) {
        return new File(path);
    }

    public URI toUrI(String path) {
        return toFile(path).getAbsoluteFile().toURI();
    }

    public URI toUrI(File file) {
        return file.getAbsoluteFile().toURI();
    }

    private ByteArrayOutputStream toByteStream(InputStream is) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toString(InputStream is) {
        try {
            return toByteStream(is).toString(UTF8.name());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String toString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return new String(bytes, UTF8);
    }

    private byte[] toBytes(InputStream is) {
        return toByteStream(is).toByteArray();
    }

    public static void deleteFile(File file) {
        file.deleteOnExit();
    }


}
