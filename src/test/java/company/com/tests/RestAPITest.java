package company.com.tests;

import company.com.constants.SeleniumCoreConstants;
import company.com.dtos.StarWarsCharactersDto;
import company.com.rest.RestHandler;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RestAPITest extends TestBase {
    @Test(groups = SeleniumCoreConstants.CUSTOMER_GROUP, description = "TC - Verify first Star Wars Character- ")
    public void useRestSpec() {
        RestHandler restHandler = new RestHandler();
        StarWarsCharactersDto starWarsCharactersDto = restHandler.useSpec("people/" + 1 + "/");

        assertEquals("Wrong character name", "Luke Skywalker", starWarsCharactersDto.getName());
    }

}
