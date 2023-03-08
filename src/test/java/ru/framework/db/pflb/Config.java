package ru.framework.db.pflb;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import ru.framework.db.pflb.managers.TestPropManager;
import ru.framework.db.pflb.utils.Props;

public class Config {
    private static final TestPropManager properties = TestPropManager.getTestPropManager();
    public static RequestSpecification requestSpecification;

    public void setUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addFilter(new AllureRestAssured());
        builder.setBaseUri(properties.getProperty(Props.BASE_URL));
        builder.addHeader("Authorization", "Bearer " + generateToken());
        builder.setAccept("application/json");
        builder.setContentType("application/json");
        requestSpecification = builder.build();
    }

    public String generateToken() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        TestPropManager.getTestPropManager();
        String line = "{\r\n" +
                "  \"username\": \"" + System.getProperty("login") + "\",\r\n" +
                "  \"password\": \"" + System.getProperty("password") + "\"\r\n" +
                "}";
        request.body(line).post(properties.getProperty(Props.BASE_URL) + "login")
                .then().log().all().extract().body();

        return request.body(line).post(properties.getProperty(Props.BASE_URL) + "login")
                .then().log().all().extract().body().path("access_token");

    }
}