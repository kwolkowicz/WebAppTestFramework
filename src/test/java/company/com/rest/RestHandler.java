package company.com.rest;

import company.com.constants.RestConstants;
import company.com.dtos.StarWarsCharactersDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestHandler {
    private static RequestSpecification spec;


    private static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(RestConstants.APP_URI)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    public StarWarsCharactersDto useSpec(String path) {
        initSpec();
        return given()
                .auth()
                .none()
                .spec(spec)
                .param("limit", 1)
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .extract().as(StarWarsCharactersDto.class);
    }
}
