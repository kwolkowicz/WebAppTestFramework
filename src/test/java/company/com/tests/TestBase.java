package company.com.tests;

import com.relevantcodes.extentreports.LogStatus;
import company.com.constants.SeleniumCoreConstants;
import company.com.constants.StatusesConstants;
import company.com.core.SuiteContext;
import company.com.core.TestBaseManager;
import company.com.dtos.TestBaseDto;
import company.com.extentReports.ExtentManager;
import company.com.extentReports.ExtentTestManager;
import company.com.helpers.TestUser;
import company.com.pageobjects.LogInPageObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.SessionNotCreatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class TestBase {
    private static final Logger LOG = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        File downloadDir = new File(SeleniumCoreConstants.FILE_DOWNLOAD_DIR);
        downloadDir.mkdir();
        FileUtils.deleteQuietly(downloadDir);

        File reportDir = new File(SeleniumCoreConstants.REPORT_DIR);
        reportDir.mkdir();
        FileUtils.deleteQuietly(reportDir);

        if (ExtentManager.getReporter() == null)
            ExtentManager.getReporter();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        LOG.info(String.format("SELENIUM TEST STARTED: %s.%s", getClass().getSimpleName(), method.getName()));
        ExtentTestManager.startTest(String.format("SELENIUM TEST STARTED: %s.%s", getClass().getSimpleName(), method.getName()));
    }

    public void initializeTest(TestBaseDto testBaseDto, String userAccessLevel) {
        initializeTestCase(testBaseDto, userAccessLevel);
    }

    public void initializeTest(TestBaseDto testBaseDto) {
        openApp(testBaseDto);
        LogInPageObject logInPageObject = new LogInPageObject(testBaseDto);
        logInPageObject.getBlueprint().waitForPageLoad();
        ExtentTestManager.log(LogStatus.PASS, "Application is open");
    }

    private void openApp(TestBaseDto testBaseDto) {
        testBaseDto.getDriver().cleanWebDriverSession();
        testBaseDto.getDriver().updateWindowReadyStateToLoading();
        testBaseDto.getDriver().navigate("");
    }

    private void initializeTestCase(TestBaseDto testBaseDto, String userAccessLevel) {
        TestUser testUser;

        if (Objects.equals(StatusesConstants.INVALID_USER_ACCESS_LEVEL, userAccessLevel)) {
            testUser = new TestUser(SeleniumCoreConstants.USER_INVALID_CREDENTIALS_NAME, SeleniumCoreConstants.USER_INVALID_CREDENTIALS_PASSWORD);
        } else {
            testUser = SuiteContext.getInstance().fetchAndReserveUser(testBaseDto, userAccessLevel);
        }

        testBaseDto.setTestUser(testUser);
        openApp(testBaseDto);

        LogInPageObject logInPageObject = new LogInPageObject(testBaseDto);

        if (Objects.equals(testUser.getLogin(), SeleniumCoreConstants.USER_INVALID_CREDENTIALS_NAME)) {
            logInPageObject.logIn(testUser);
        } else {
            logInPageObject.logInAndWaitForNewPage(testUser);
        }

        ExtentTestManager.log(LogStatus.PASS, "User " + testUser.getLogin() + " logged successfully to the application");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        Reporter.setCurrentTestResult(result);

        if (result.getThrowable() instanceof SessionNotCreatedException) {
            LOG.info(String.format("SELENIUM TEST FAILED: %s.%s due to SessionNotCreatedException",
                    getClass().getSimpleName(), result.getMethod().getMethodName()));
            ExtentTestManager.log(LogStatus.FAIL, String.format("SELENIUM TEST FAILED: %s.%s due to SessionNotCreatedException",
                    getClass().getSimpleName(), result.getMethod().getMethodName()));
            return;
        }

        if (result.getThrowable() instanceof URISyntaxException) {
            ExtentTestManager.log(LogStatus.FAIL, String.format("SELENIUM TEST FAILED: %s.%s due to Ingest file copy failed",
                    getClass().getSimpleName(), result.getMethod().getMethodName()));
            return;
        }

        TestBaseDto testBaseDto = TestBaseManager.fetchTestBaseInstance();
        String executionTime = new SimpleDateFormat("mm:ss:SSS")
                .format(new Date(result.getEndMillis() - result.getStartMillis()));

        if (result.isSuccess()) {
            LOG.info(String.format(
                    "SELENIUM TEST SUCCEED: %s.%s USED USERS: %s EXECUTION TIME: %s",
                    getClass().getSimpleName(), result.getMethod().getMethodName(), testBaseDto.getReservedUserNames(), executionTime));
            ExtentTestManager.log(LogStatus.PASS, String.format(
                    "SELENIUM TEST SUCCEED: %s.%s USED USERS: %s EXECUTION TIME: %s",
                    getClass().getSimpleName(), result.getMethod().getMethodName(), testBaseDto.getReservedUserNames(), executionTime));
        } else {

            LOG.info(String.format(
                    "SELENIUM TEST FAILED: %s.%s USED USERS: %s EXECUTION TIME: %s",
                    getClass().getSimpleName(), result.getMethod().getMethodName(), testBaseDto.getReservedUserNames(), executionTime));
            ExtentTestManager.logWithScreenShot(LogStatus.FAIL, String.format(
                    "SELENIUM TEST FAILED: %s.%s USED USERS: %s EXECUTION TIME: %s",
                    getClass().getSimpleName(), result.getMethod().getMethodName(), testBaseDto.getReservedUserNames(), executionTime),
                    testBaseDto.getDriver().takeScreenshot(result.getMethod().getMethodName()));
        }

        SuiteContext.getInstance().cancelUsersReservation(testBaseDto.getReservedUserNames());
        TestBaseManager.removeDriver();
        ExtentTestManager.endTest();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        File downloadDir = new File(SeleniumCoreConstants.FILE_DOWNLOAD_DIR);
        FileUtils.deleteQuietly(downloadDir);
        ExtentManager.getReporter().close();
    }
}
