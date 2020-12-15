package util;

import io.qameta.allure.Allure;
import java.util.Base64;

public class AllureAttachmentUtil {

    private String name;
    private String content;

    public AllureAttachmentUtil(){
    }

    /** attachment type actually mime type, default tpe is text/plain **/

    private String requestTemplatePath = "http-request.ftl";
    private String responseTemplatePath = "http-response.ftl";

    public static void addAttachmentAsTextPlain(String name, String content) {
        Allure.addAttachment(name, content);
    }

    public static void addAttachmentAsJson(String name, String content) {
        Allure.addAttachment(name, content, "application/json", ".json");
    }

    public static void addAttachmentAsCsv(String name, String content) {
        Allure.addAttachment(name, content, "text/csv", ".csv");
    }

    public static void addAttachmentAsImageJpeg(String name, byte[] content) {
        byte[] decode = Base64.getMimeDecoder().decode(content);
        Allure.addByteAttachmentAsync(name, "image/jpeg", ".jpg", ()->decode);
    }

    public static void addAttachmentAsImagePng(String name, byte[] content) {
        byte[] decode = Base64.getMimeDecoder().decode(content);
        Allure.addByteAttachmentAsync(name, "image/png", ".png", ()->decode);
    }

    public static void addAttachmentAsAudio(String name, String content) {
        byte[] decode = Base64.getMimeDecoder().decode(content);
        Allure.addByteAttachmentAsync(name, "audio/aac", ".aac",()->decode);
    }

}
