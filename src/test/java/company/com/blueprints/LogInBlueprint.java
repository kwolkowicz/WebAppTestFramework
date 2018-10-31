package company.com.blueprints;

import company.com.core.SeleniumCore;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInBlueprint {

    protected SeleniumCore driver;

    @FindBy(name = "userName")
    private WebElement usernameTextArea;

    @FindBy(name = "password")
    private WebElement passwordTextArea;

    @FindBy(name = "login")
    private WebElement logInButton;

    @FindBy(xpath = "//*[contains(@class, 'scLoginBox')]/div[contains(text(), 'Error')]")
    private WebElement loginErrorMessage;


    public LogInBlueprint(SeleniumCore driver) {
        this.driver = driver;
        this.driver.waitForDocumentReadyStateComplete();
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void overwriteUsernameText(String username) {
        driver.overwriteInputFieldValueWithoutScroll(usernameTextArea, username);
    }

    public void overwritePasswordText(String password) {
        driver.overwriteInputFieldValueWithoutScroll(passwordTextArea, password);
    }

    public void clickLogInButton() {
        driver.clickElement(logInButton);
    }

    public void waitForPageLoad() {
        driver.waitForPageLoad();
    }

    public String getUnableToLoginMessage() {
        return loginErrorMessage.getText();
    }
}
