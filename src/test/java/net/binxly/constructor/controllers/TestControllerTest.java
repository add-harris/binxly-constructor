package net.binxly.constructor.controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import net.binxly.constructor.TestProfileConfig;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
@TestProfile(TestProfileConfig.class)
class TestControllerTest {

    @Test
    public void test_construct_controller() {
        given()
                .when().get("/test")
                .then()
                .statusCode(200);
    }

}