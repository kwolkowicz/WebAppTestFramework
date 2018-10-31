package company.com.tests;

import company.com.constants.SeleniumCoreConstants;
import company.com.constants.StatusesConstants;
import company.com.pageobjects.DashboardPageObject;
import company.com.core.TestBaseManager;
import company.com.dtos.TestBaseDto;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LogInTest extends TestBase {

    @Test(groups = SeleniumCoreConstants.CUSTOMER_GROUP, description = "TC - log in as customer - ")
    public void logInCustomer() {
        TestBaseDto testBaseDto = TestBaseManager.fetchTestBaseInstance();
        initializeTest(testBaseDto, StatusesConstants.CUSTOMER_ACCESS_LEVEL);

        DashboardPageObject dashboardPageObject = new DashboardPageObject(testBaseDto);
        assertTrue("Wrong page loaded", dashboardPageObject.getBlueprint().isFindFlightPaneDisplayed());

    }

    @Test(groups = SeleniumCoreConstants.CUSTOMER_GROUP, description = "TC - log in with invalid credentials - ")
    public void logInUserInvalidCredentials() {
        TestBaseDto testBaseDto = TestBaseManager.fetchTestBaseInstance();
        initializeTest(testBaseDto, StatusesConstants.INVALID_USER_ACCESS_LEVEL);

        DashboardPageObject dashboardPageObject = new DashboardPageObject(testBaseDto);
        assertFalse("Wrong page loaded", dashboardPageObject.getBlueprint().isFindFlightPaneDisplayed());

    }
}
