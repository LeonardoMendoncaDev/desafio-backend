package digital.b2w.ri.rest.resources;

import digital.b2w.ri.rest.client.SwapiClient;
import digital.b2w.ri.rest.exception.EmptyPlanet;
import digital.b2w.ri.rest.models.Planet;
import digital.b2w.ri.rest.services.PlanetService;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/planets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanetResource {

    private Logger logger = LoggerFactory.getLogger(PlanetResource.class);

    @Inject
    PlanetService planetService;

    @Inject
    SwapiClient swapiClient;

    @GET
    public Response getAll() {
        Set<Planet> planets = planetService.getAll();
        return Response
                .ok(planets)
                .entity(planets)
                .build();
    }

    @GET
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String name) {
        Set<Planet> planets = planetService.getByName(name);
        return Response
                .ok(planets)
                .header("status", Response.Status.OK)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        Set<Planet> planets = planetService.getById(id);
        return Response.ok(planets)
                .header("status", Response.Status.OK)
                .build();
    }

    @POST
    public Response add(Planet planet) {
        Optional.ofNullable(planet.getName()).orElseThrow(EmptyPlanet::new);
        planetService.add(planet);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") String id) {
        planetService.remove(id);
        return getAll();
    }

}