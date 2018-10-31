package company.com.core;

import company.com.constants.SeleniumCoreConstants;
import company.com.constants.StatusesConstants;
import company.com.dtos.TestBaseDto;
import company.com.helpers.TestUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuiteContext {
    private static final Logger LOG = LoggerFactory.getLogger(SuiteContext.class);

    private static SuiteContext suiteContext;

    private static List<String> reservedUserNames;


    private SuiteContext() {
        reservedUserNames = new ArrayList<>();

        LOG.info("SELENIUM TEST: new SuiteContext instance was created");
    }

    public static synchronized SuiteContext getInstance() {
        if (suiteContext == null) {
            suiteContext = new SuiteContext();
        }

        return suiteContext;
    }

    public synchronized TestUser fetchAndReserveUser(TestBaseDto testBaseDto, String userAccessLevel) {
        LocalDateTime timeout = LocalDateTime.now().plusSeconds(SeleniumCoreConstants.CHECK_AVAILABILITY_TIMEOUT_IN_SECONDS);

        while (LocalDateTime.now().isBefore(timeout)) {

            for (String userName : SeleniumCoreConstants.USER_NAMES) {
                if (!reservedUserNames.contains(userName)) {
                    reservedUserNames.add(userName);
                    testBaseDto.addReservedUserName(userName);
                    LOG.info("SELENIUM TEST: user '" + userName + "' was reserved; all reserved users - ("
                            + reservedUserNames + ')');

                    String userPassword = Objects.equals(StatusesConstants.CUSTOMER_ACCESS_LEVEL, userAccessLevel) ?
                            SeleniumCoreConstants.CUSTOMER_PASSWORD :
                            SeleniumCoreConstants.WORKER_PASSWORD;

                    return new TestUser(userName, userPassword);
                }
            }
            testBaseDto.getDriver().sleep(10);
        }

        throw new IllegalStateException(
                "SELENIUM TEST: no available user; found users - (" + SeleniumCoreConstants.USER_NAMES + "); all reserved users - ("
                        + reservedUserNames + ')');
    }

    public synchronized void cancelUsersReservation(List<String> userNames) {
        if (!userNames.isEmpty()) {
            reservedUserNames.removeAll(userNames);

            LOG.info("SELENIUM TEST: reservation for users was canceled (" + userNames + "); all reserved users - ("
                    + reservedUserNames + ')');
        }
    }

    public static synchronized int generateRandomPhone() {
        return (int) ((Math.random() * 900000000) + 100000000);
    }

    public static synchronized int generateRandomPostalCode() {
        return (int) ((Math.random() * 900000) + 100000);
    }
}
