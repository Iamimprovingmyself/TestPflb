package ru.framework.db.pflb;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.framework.db.pflb.model.Car;
import ru.framework.db.pflb.model.User;

import static io.restassured.RestAssured.given;

class StepsTest {
    Steps steps = new Steps();

    @DisplayName("Получение списка всех машин")
    @Test
    public void getAllCars() {
        Response response = given().spec(Config.requestSpecification).get("cars")
                .then().log().all()
                .extract()
                .response();
    }

    @BeforeAll
    public static void init() {
        Config config = new Config();
        config.setUp();
    }

    @DisplayName("Создание машины")
    @Test
    public void createCar() {
        steps.newCar(new Car("Electric", "Tesla", "QWe", 43000.00))
                .statusCode(201)
                .body("model", Matchers.is("QWe"));
    }

    @DisplayName("Создание пользователя")
    @Test
    public void createUser() {
        steps.newUser(new User("Auto", "ewqeq", 30, "MALE", 0.00))
                .statusCode(201)
                .body("secondName", Matchers.is("ewqeq"));
    }

    @Test
    public void setMoneyForUser() {
        steps.setMoney(295, 5000.00F)
                .statusCode(201)
                .body("money", Matchers.is(5000.0F));
    }
}