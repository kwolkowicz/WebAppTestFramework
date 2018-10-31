package company.com.components;

import company.com.core.SeleniumCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SingleSelectDropdownComponent {
    private static final String DROPDOWN_XPATH = ".//*[contains(@name, '%s')]";
    private static final String LIST_MENU_XPATH = ".//*[contains(@name, '%s')]//*";
    private static final String LIST_ELEMENT_XPATH = "//div//*";

    private final SeleniumCore driver;
    private final String dropdownXpath;
    private final String listMenuXpath;
    private final boolean isScrollable;

    public SingleSelectDropdownComponent(SeleniumCore driver, String tagRawId) {
        this.driver = driver;
        this.dropdownXpath = String.format(DROPDOWN_XPATH, tagRawId);
        this.listMenuXpath = String.format(LIST_MENU_XPATH, tagRawId);
        this.isScrollable = true;
    }

    public void clickDropdown() {
        driver.clickElement(fetchDropdown());
        waitUntilDropdownListIsVisible();
    }

    public void selectElement(String value) {
        clickDropdown();
        WebElement element = fetchElement(value);
        if (isScrollable) {
            driver.scrollToElement(element);
            driver.getWait().until(ExpectedConditions.elementToBeClickable(element));
        }
        element.click();
    }

    private void waitUntilDropdownListIsVisible() {
        driver.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(listMenuXpath)));
    }

    private WebElement fetchElement(String value) {
        return fetchDropdownListMenu().findElement(By.xpath(".//*[text() = \"" + value + "\"]"));
    }

    private WebElement fetchDropdownListMenu() {
        return driver.findElement(By.xpath(listMenuXpath));
    }

    private WebElement fetchDropdown() {
        return driver.findElement(By.xpath(dropdownXpath));
    }

    public String fetchDropdownSelectedValue() {
        driver.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(listMenuXpath)));
        return driver.findElement(By.xpath(dropdownXpath)).getAttribute("value");
    }
}
