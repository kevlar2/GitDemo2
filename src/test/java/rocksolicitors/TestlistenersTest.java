package rocksolicitors;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestlistenersTest implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + result.getName() + " Completed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test " + result.getName() + " Test Failed");
        String testName = result.getName();
        FailedTestScreenshots.takesScreenshotOfFailedTest(testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test " + result.getName() + " Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("System Starting Up");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("System Shutting down");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting " + result.getName() +" Test");
    }
}
