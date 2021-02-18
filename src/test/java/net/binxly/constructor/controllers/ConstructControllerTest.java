package net.binxly.constructor.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ConstructControllerTest {

    @Test
    public void test_construct_controller() {
        given()
                .when().get("/test")
                .then()
                .statusCode(200);
    }

}