package company.com.pageobjects;

import company.com.blueprints.DashboardBlueprint;
import company.com.core.SeleniumCore;
import company.com.dtos.TestBaseDto;

public class DashboardPageObject {
    final SeleniumCore driver;
    final TestBaseDto testBaseDto;
    private DashboardBlueprint dashboardBlueprint;

    public DashboardPageObject(TestBaseDto testBaseDto) {
        this.testBaseDto = testBaseDto;
        driver = this.testBaseDto.getDriver();
        dashboardBlueprint = new DashboardBlueprint(this.testBaseDto.getDriver());
    }

    public DashboardBlueprint getBlueprint() {
        return dashboardBlueprint;
    }

    public void goToRegisterPage() {
        dashboardBlueprint.clickRegisterButton();
    }
}
