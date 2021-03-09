package net.binxly.constructor.controllers;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import net.binxly.constructor.dto.BuildRequestDTO;
import net.binxly.constructor.models.NavBar;
import net.binxly.constructor.services.ConstructionService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@QuarkusTest
@TestHTTPEndpoint(ConstructController.class)
class ConstructControllerTest {

    @InjectSpy
    ConstructionService constructionService;

    BuildRequestDTO buildRequestDTO = new BuildRequestDTO(new NavBar("test"), List.of());

    @Test
    public void construct_controller_returns_200() {

        given()
            .contentType("application/json")
            .with().body(buildRequestDTO)
            .when().post()
            .then()
            .statusCode(200);
    }

    @Test
    public void construct_controller_calls_construction_service() {

        given()
                .contentType("application/json")
                .with().body(buildRequestDTO)
                .when().post()
                .then()
                .statusCode(200);

        verify(constructionService, times(1)).doNothing();

    }

}