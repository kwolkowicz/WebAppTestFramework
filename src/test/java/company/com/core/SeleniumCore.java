package company.com.core;

import company.com.constants.SeleniumCoreConstants;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SeleniumCore {

    private static final Logger LOG = LoggerFactory.getLogger(SeleniumCore.class);

    private static final String SCREENSHOT_PATH = "target/screenshots/";
    private static final String CHROME_DRIVER_DOCKER_PATH = "/usr/bin/chromedriver";

    private final File downloadDir;

    private final WebDriver driver;
    private final WebDriverWait wait;

    private ChromeDriverService driverService;

    public SeleniumCore() {
        downloadDir = new File(SeleniumCoreConstants.FILE_DOWNLOAD_DIR);

        if (!downloadDir.exists()) {
            downloadDir.mkdir();
        }

        if (SystemUtils.IS_OS_WINDOWS) {
            driver = initializeDriverSession();
        } else {
            driver = initializeHeadlessDriverSession();
        }

        driver.manage().window().setSize(new Dimension(1366, 768));

        wait = new WebDriverWait(driver, 80, 2000);
    }

    private static File prepareScreenshotFile(String methodName) {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        String fileDate = SeleniumCoreConstants.PATTERN_COLON.matcher(currentTime.toString()).replaceAll("-");
        String fileName = SeleniumCoreConstants.PATTERN_SPACE.matcher(fileDate).replaceAll("_") + '_' + methodName + ".png";
        String absoluteFileName = SCREENSHOT_PATH + fileName;

        return new File(absoluteFileName);
    }

    private WebDriver initializeDriverSession() {
        ChromeDriverManager.getInstance().setup();

        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadDir.getAbsolutePath());
        chromePrefs.put("download.prompt_for_download", false);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setExperimentalOption("prefs", chromePrefs);
        browserOptions.addArguments("--disable-extensions");
        browserOptions.addArguments("--no-sandbox");

        return new ChromeDriver(browserOptions);
    }

    private WebDriver initializeHeadlessDriverSession() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_DOCKER_PATH);

        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadDir.getAbsolutePath());
        chromePrefs.put("download.prompt_for_download", false);

        Map<String, Object> commandParams = new HashMap<>();
        commandParams.put("cmd", "Page.setDownloadBehavior");
        commandParams.put("params", chromePrefs);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setExperimentalOption("prefs", commandParams);
        browserOptions.addArguments("--disable-extensions");
        browserOptions.addArguments("--no-sandbox");
        browserOptions.addArguments("--dns-prefetch-disable");
        browserOptions.addArguments("--headless");
        browserOptions.addArguments("--disable-gpu");
        browserOptions.addArguments("--hide-scrollbars");

        driverService = ChromeDriverService.createDefaultService();
        return new ChromeDriver(driverService, browserOptions);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void cleanWebDriverSession() {
        driver.manage().deleteAllCookies();
    }

    public void navigate(String URL) {
        if (System.getProperty("environment") == null) {
            driver.get(SeleniumCoreConstants.HTTP_ENV_ADDRESS + URL);
        } else {
            driver.get(System.getProperty("environment") + URL);
        }
    }

    public void waitForPageLoad() {
        waitForDocumentReadyStateComplete();
        waitForButtonsVisibility();
    }

    private void waitForButtonsVisibility() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class = 'mouseOut']")));
    }

    public void updateWindowReadyStateToLoading() {
        executeScript("document.readyState = 'loading';");
    }

    private void switchToFrame(String frameId) {
        driver.switchTo().frame(frameId);
        waitForDocumentReadyStateComplete();
    }

    private void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
        waitForDocumentReadyStateComplete();
    }


    public void close() {
        driver.close();
    }

    public void waitForDocumentReadyStateComplete() {
        wait.until((ExpectedCondition<Boolean>) d -> Objects
                .equals(executeScript("return document.readyState"), "complete"));
    }

    private Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public boolean isElementDisplayed(String xpath) {
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public void overwriteInputFieldValueWithoutScroll(WebElement inputField, String value) {
        if (value.isEmpty()) {
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        } else {
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
        }
    }

    public String takeScreenshot(String methodName) {
        driver.manage().window().setSize(new Dimension(1366, 13600));
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File screenshotFile = prepareScreenshotFile(methodName);

        try {
            FileUtils.copyFile(scrFile, screenshotFile);
        } catch (IOException e) {
            LOG.error("Unable to create screenshot", e);
        }
        return screenshotFile.getAbsolutePath();
    }

    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public void clickAndWaitForPageLoad(WebElement element) {
        element.click();
        waitForPageLoad();
    }

    public void clickAndWaitForPageLoadWithFrameChange(WebElement element, String frameId) {
        element.click();
        waitForPageLoad();
        switchToFrame(frameId);
    }

    public void clickAndWaitForPageLoadWithBackToDefaultFrame(WebElement element) {
        element.click();
        waitForPageLoad();
        switchToDefaultFrame();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void scrollToElement(WebElement element) {
        executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'end', inline: 'end'});", element);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }


}
