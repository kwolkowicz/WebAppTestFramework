package company.com.dtos;

import company.com.helpers.TestUser;
import company.com.core.SeleniumCore;

import java.util.ArrayList;
import java.util.List;

public class TestBaseDto {
    private SeleniumCore driver;
    private TestUser testUser;
    private List<String> reservedUserNames = new ArrayList<>();

    public SeleniumCore getDriver() {
        return driver;
    }

    public void setDriver(SeleniumCore driver) {
        this.driver = driver;
    }

    public TestUser getTestUser() {
        return testUser;
    }

    public void setTestUser(TestUser testUser) {
        this.testUser = testUser;
    }

    public List<String> getReservedUserNames() {
        return reservedUserNames;
    }

    public void addReservedUserName(String reservedUserName) {
        reservedUserNames.add(reservedUserName);
    }

}
