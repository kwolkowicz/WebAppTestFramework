package company.com.extentReports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static Map extentTestMap = new HashMap();
    private static ExtentReports extent = ExtentManager.getReporter();

    private static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.startTest(testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    public synchronized static void log(LogStatus s1, String message) {
        getTest().log(s1, message);
    }

    public synchronized static void logWithScreenShot(LogStatus s1, String message, String screenShotPath) {
        try {
            getTest().log(s1, message);
            getTest().log(s1, ExtentTestManager.getTest().addScreenCapture(screenShotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}