package company.com.pageobjects;

import company.com.blueprints.LogInBlueprint;
import company.com.core.SeleniumCore;
import company.com.dtos.TestBaseDto;
import company.com.helpers.TestUser;

public class LogInPageObject {
    final SeleniumCore driver;
    final TestBaseDto testBaseDto;
    private final LogInBlueprint logInBlueprint;

    public LogInPageObject(TestBaseDto testBaseDto) {
        this.testBaseDto = testBaseDto;
        driver = this.testBaseDto.getDriver();
        logInBlueprint = new LogInBlueprint(this.testBaseDto.getDriver());
    }

    public LogInBlueprint getBlueprint() {
        return logInBlueprint;
    }

    public void logInAndWaitForNewPage(TestUser user) {
        logIn(user);
        logInBlueprint.waitForPageLoad();
    }

    public void logIn(TestUser user) {
        logInBlueprint.overwriteUsernameText(user.getLogin());
        logInBlueprint.overwritePasswordText(user.getPassword());
        logInBlueprint.clickLogInButton();
    }
}
