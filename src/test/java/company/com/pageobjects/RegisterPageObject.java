package company.com.pageobjects;

import company.com.blueprints.RegisterBlueprint;
import company.com.core.SeleniumCore;
import company.com.dtos.NewUserDto;
import company.com.dtos.TestBaseDto;

import static org.easetech.easytest.util.GeneralUtil.convertToString;

public class RegisterPageObject {
    final SeleniumCore driver;
    final TestBaseDto testBaseDto;
    private RegisterBlueprint registerBlueprint;

    public RegisterPageObject(TestBaseDto testBaseDto) {
        this.testBaseDto = testBaseDto;
        driver = this.testBaseDto.getDriver();
        registerBlueprint = new RegisterBlueprint(this.testBaseDto.getDriver());
    }

    public RegisterBlueprint getBlueprint() {
        return registerBlueprint;
    }

    public void fillRegisterForm(NewUserDto newUserDto) {
        provideContactData(newUserDto);
        provideMailingData(newUserDto);
        provideUserInfoData(newUserDto);
    }

    private void provideContactData(NewUserDto newUserDto) {
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getFirstName(), newUserDto.getFirstName());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getLastName(), newUserDto.getLastName());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getPhone(), convertToString(newUserDto.getPhoneNumber()));
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getEmail(), newUserDto.getEmail());
    }

    private void provideMailingData(NewUserDto newUserDto) {
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getAddres(), newUserDto.getAddress());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getCity(), newUserDto.getCity());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getState(), newUserDto.getState());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getPostal(), convertToString(newUserDto.getPostalCode()));
        getBlueprint().selectCountry(newUserDto.getCountryName());
    }

    private void provideUserInfoData(NewUserDto newUserDto) {
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getUserName(), newUserDto.getUserName());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getPassword(), newUserDto.getPassword());
        driver.overwriteInputFieldValueWithoutScroll(getBlueprint().getConfirmPassword(), newUserDto.getPassword());
    }

}
