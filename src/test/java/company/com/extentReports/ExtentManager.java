package company.com.extentReports;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static company.com.constants.SeleniumCoreConstants.REPORT_CONFIG_DIR;
import static company.com.constants.SeleniumCoreConstants.REPORT_DIR;


public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hhmmss");
            String strDate = dateFormat.format(date);
            extent = new ExtentReports(REPORT_DIR + "\\TestRunReport" + strDate + ".html", true);
            extent.loadConfig(new File(REPORT_CONFIG_DIR));
        }
        return extent;
    }
}