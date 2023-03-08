package ru.framework.db.pflb;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.framework.db.pflb.model.Car;
import ru.framework.db.pflb.model.User;
import static io.restassured.RestAssured.given;

public class Steps {
    public ValidatableResponse setMoney (Integer id, float money) {
        RequestSpecification basicRequest = given().spec(Config.requestSpecification);
        basicRequest.get("user/"+id).then().statusCode(204);
        return basicRequest.post("user/"+id+"/money/"+money)
                .then().log().all();
    }

    public ValidatableResponse newUser(User user) {
        RequestSpecification requestSpecification = given().spec(Config.requestSpecification).body(user);
        return requestSpecification.post("user")
                .then().log().all();

    }
    public ValidatableResponse newCar(Car car) {
        return given().spec(Config.requestSpecification).body(car).post("car")
                .then().log().all();

    }
}