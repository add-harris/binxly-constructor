package net.binxly.constructor.controllers;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectMock;
import net.binxly.constructor.models.BuildRequest;
import net.binxly.constructor.models.NavBar;
import net.binxly.constructor.TestProfileConfig;
import net.binxly.constructor.services.OrchestrationService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@QuarkusTest
@TestProfile(TestProfileConfig.class)
@TestHTTPEndpoint(ConstructionController.class)
class ConstructionControllerTest {

    @InjectMock
    OrchestrationService orchestrationService;

    BuildRequest buildRequest = new BuildRequest(
            "123456",
            "test-project",
            new NavBar("test"),
            List.of());

    @Test
    public void construct_controller_returns_200() {

        given()
            .contentType("application/json")
            .with().body(buildRequest)
            .when().post()
            .then()
            .statusCode(200);
    }

    @Test
    public void construct_controller_calls_construction_service() {

        given()
                .contentType("application/json")
                .with().body(buildRequest)
                .when().post()
                .then()
                .statusCode(200);

        verify(orchestrationService, times(1)).orchestrate(any(BuildRequest.class));

    }

}