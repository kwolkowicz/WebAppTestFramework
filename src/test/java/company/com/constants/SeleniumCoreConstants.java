package company.com.constants;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SeleniumCoreConstants {

    public static final String USER_INVALID_CREDENTIALS_NAME = "userInvalidCredentialsName";
    public static final String USER_INVALID_CREDENTIALS_PASSWORD = "userInvalidCredentialsPassword";
    public static final String CUSTOMER_PASSWORD = "tutorial";
    public static final String WORKER_PASSWORD = "tutorial";

    public static final String CUSTOMER_GROUP = "customerGroup";

    public static final int CHECK_AVAILABILITY_TIMEOUT_IN_SECONDS = 100;

    public static final Pattern PATTERN_SPACE = Pattern.compile(" ");
    public static final Pattern PATTERN_COLON = Pattern.compile(":");
    public static final Pattern PATTERN_SLASH = Pattern.compile("/");

    public static final String FILE_DOWNLOAD_DIR = "target/downloads/";
    public static final String REPORT_DIR = "target/report/";
    public static final String REPORT_CONFIG_DIR = "reportConfig/extent-config.xml";
    public static final String SYSTEM_TEST_FILES_PATH = "FILE:////pyramid/FlightInfoAppDev/SourceIngest/SystemTestFiles";

    public static final String SOURCE_DATA_PAGE_TITLE = "Source Data";

    private static final String ENV_ADDRESS = "newtours.demoaut.com";
    public static final String HTTP_ENV_ADDRESS = "http://" + ENV_ADDRESS + '/';

    public static final List<String> USER_NAMES = Arrays.asList("tutorial");

    private SeleniumCoreConstants() {
        // util class
    }
}