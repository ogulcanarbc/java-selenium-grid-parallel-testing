package rule;

import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Testi dinler, mevcut koşan test bilgilerini ve durumunu loglayabilirsiniz.
 */
public class TestListener extends TestListenerAdapter implements ITestListener {

    protected Logger logger = Logger.getLogger(TestListener.class);


    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(iTestResult.getName() + " Test Start.");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(iTestResult.getName() + " Test is Success.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info(iTestResult.getName() + " Test is Fail.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(iTestResult.getName() + " Test is Skipped.");
    }

}
