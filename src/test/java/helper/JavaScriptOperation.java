package helper;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptOperation {
    WebDriver jsDriver = new BaseTest().getDriver();

    public JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) this.jsDriver;
    }

    public Object executeJS(String jsStmt) {
        return this.getJSExecutor().executeScript(jsStmt, new Object[0]);
    }

    public Object executeJS(String script, Object... obj) {
        return this.getJSExecutor().executeScript(script, obj);
    }
}
