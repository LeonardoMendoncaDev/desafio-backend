package digital.b2w.ri.rest.resources;

import digital.b2w.ri.rest.models.Planet;
import digital.b2w.ri.rest.services.PlanetService;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matcher.*;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PlanetResourceTest {

    @Inject
    PlanetService planetService;

    @Test
    public void testGetAll() {
        given()
                .when()
                .get("/planets/")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200);
    }

    @Test
    public void testGetByName() {
        String name = "Alderaan";
        String planet = "[{\"id\":\"5e55c87f262e7e4d11f1e007\",\"name\":\"Alderaan\",\"climate\":\"Clima43\",\"terrain\":\"Terreno43\",\"appears\":\"2\"}]";

        given()
                .pathParam("name", name)
                .when()
                .get("/planets/name/{name}")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200)
                .body(org.hamcrest.CoreMatchers.is(planet));

    }

    @Test
    public void testGetById() {
        String paramId = "5e55c87f262e7e4d11f1e007";
        String planet = "[{\"id\":\"5e55c87f262e7e4d11f1e007\",\"name\":\"Alderaan\",\"climate\":\"Clima43\",\"terrain\":\"Terreno43\",\"appears\":\"2\"}]";

        given()
                .pathParam("paramId", paramId)
                .when()
                .get("/planets/{paramId}")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200)
                .body(org.hamcrest.CoreMatchers.is(planet));

    }

    @Test
    public void testAdd() {

        Map<String,String> planet = new HashMap<>();
        planet.put("name", "Tatooine");
        planet.put("climate", "ClimaXPTO");
        planet.put("terrain", "TerrenoXPTO");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(planet)
                .when()
                .post("/planets/")
                .then()
                .statusCode(201);

    }

    @Test
    public void testRemove() {

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", "5e4748babd721c74f40c6059")
                .when()
                .delete("/planets/{id}")
                .then()
                .statusCode(200);

        given()
                .pathParam("paramId", "5e4748babd721c74f40c6059")
                .when()
                .get("/planets/{paramId}")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200)
                .body(org.hamcrest.CoreMatchers.is("[]"));

    }

}