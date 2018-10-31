package company.com.tests;

import company.com.constants.SeleniumCoreConstants;
import company.com.core.SuiteContext;
import company.com.core.TestBaseManager;
import company.com.dtos.NewUserDto;
import company.com.dtos.TestBaseDto;
import company.com.pageobjects.DashboardPageObject;
import company.com.pageobjects.RegisterPageObject;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class RegisterTest extends TestBase {

    @Test(groups = SeleniumCoreConstants.CUSTOMER_GROUP, description = "TC - Create simply entry - ")
    public void registerNewUser() {
        TestBaseDto testBaseDto = TestBaseManager.fetchTestBaseInstance();
        initializeTest(testBaseDto);

        DashboardPageObject dashboardPageObject = new DashboardPageObject(testBaseDto);
        assertTrue("Wrong page loaded", dashboardPageObject.getBlueprint().isHomePaneDisplayed());
        dashboardPageObject.goToRegisterPage();

        // -----TEMPORARY DATA PREPARATION------>
        NewUserDto newUserData = new NewUserDto("userTestName", "userLastTestName", SuiteContext.generateRandomPhone(), "some@mail.com", "testAddress",
                "Gdynia", "Pomerania", SuiteContext.generateRandomPostalCode(), "POLAND", "username", "password");
        // -----TEMPORARY DATA PREPARATION------<

        RegisterPageObject registerPageObject = new RegisterPageObject(testBaseDto);
        registerPageObject.fillRegisterForm(newUserData);
        registerPageObject.getBlueprint().clickSubmitButton();

        assertTrue("Wrong page loaded", dashboardPageObject.getBlueprint().isWelcomeNewRegisteredUserInfoDisplayed());
    }
}
