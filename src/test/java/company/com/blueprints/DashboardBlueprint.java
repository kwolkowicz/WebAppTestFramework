package company.com.blueprints;

import company.com.core.SeleniumCore;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class DashboardBlueprint {
    protected SeleniumCore driver;

    private static final String REGISTER_BUTTON_XPATH = ".//*[contains(text(), 'REGISTER')]";
    private static final String FIND_FLIGHT_PANE_XPATH = ".//*[@name = 'findflight']";
    private static final String WELCOME_NEW_USER_XPATH = ".//*[contains(text(), ' Note: Your user name is')]";
    private static final String HOME_PANE_XPATH = ".//*[@name = 'home']";

    public DashboardBlueprint(SeleniumCore driver) {
        this.driver = driver;
        this.driver.waitForDocumentReadyStateComplete();
        PageFactory.initElements(driver.getDriver(), this);

    }

    public boolean isFindFlightPaneDisplayed() {
        return driver.isElementDisplayed(FIND_FLIGHT_PANE_XPATH);
    }

    public boolean isHomePaneDisplayed() {
        return driver.isElementDisplayed(HOME_PANE_XPATH);
    }

    public void clickRegisterButton() {
        driver.clickAndWaitForPageLoad(driver.findElement(By.xpath(REGISTER_BUTTON_XPATH)));
    }

    public boolean isWelcomeNewRegisteredUserInfoDisplayed() {
        return driver.isElementDisplayed(WELCOME_NEW_USER_XPATH);
    }
}
