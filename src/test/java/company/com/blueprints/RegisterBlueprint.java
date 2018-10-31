package company.com.blueprints;

import company.com.core.SeleniumCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.testng.AssertJUnit.assertEquals;

public class RegisterBlueprint {
    protected SeleniumCore driver;

    private final Select countryDropdown;

    private static final String COUNTRY_DROPDOWN_LIST_TAG_RAW_ID = "country";
    private static final String FIRST_NAME_XPATH = ".//*[@name = 'firstName']";
    private static final String LAST_NAME_XPATH = ".//*[@name = 'lastName']";
    private static final String PHONE_XPATH = ".//*[@name = 'phone']";
    private static final String EMAIL_XPATH = ".//*[@name = 'userName']";
    private static final String ADDRESS_XPATH = ".//*[@name = 'address1']";
    private static final String CITY_XPATH = ".//*[@name = 'city']";
    private static final String STATE_XPATH = ".//*[@name = 'state']";
    private static final String POSTAL_CODE_XPATH = ".//*[@name = 'postalCode']";
    private static final String USERNAME_XPATH = ".//*[@name = 'email']";
    private static final String PASSWORD_XPATH = ".//*[@name = 'password']";
    private static final String CONFIRM_PASSWORD_XPATH = ".//*[@name = 'confirmPassword']";
    private static final String SUBMIT_BUTTON_XPATH = ".//*[@name = 'register']";


    public RegisterBlueprint(SeleniumCore driver) {
        this.driver = driver;
        countryDropdown = new Select(driver.findElement(By.name(COUNTRY_DROPDOWN_LIST_TAG_RAW_ID)));
    }

    public WebElement getFirstName() {
        return driver.findElement(By.xpath(FIRST_NAME_XPATH));
    }

    public WebElement getLastName() {
        return driver.findElement(By.xpath(LAST_NAME_XPATH));
    }

    public WebElement getPhone() {
        return driver.findElement(By.xpath(PHONE_XPATH));
    }

    public WebElement getEmail() {
        return driver.findElement(By.xpath(EMAIL_XPATH));
    }

    public WebElement getAddres() {
        return driver.findElement(By.xpath(ADDRESS_XPATH));
    }

    public WebElement getCity() {
        return driver.findElement(By.xpath(CITY_XPATH));

    }

    public WebElement getState() {
        return driver.findElement(By.xpath(STATE_XPATH));
    }

    public WebElement getPostal() {
        return driver.findElement(By.xpath(POSTAL_CODE_XPATH));
    }

    public WebElement getUserName() {
        return driver.findElement(By.xpath(USERNAME_XPATH));
    }

    public WebElement getPassword() {
        return driver.findElement(By.xpath(PASSWORD_XPATH));

    }

    public WebElement getConfirmPassword() {
        return driver.findElement(By.xpath(CONFIRM_PASSWORD_XPATH));
    }

    public void selectCountry(String countryName) {
        countryDropdown.selectByVisibleText(countryName);
    }

    public void clickSubmitButton() {
        driver.clickAndWaitForPageLoad(driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH)));
    }
}
