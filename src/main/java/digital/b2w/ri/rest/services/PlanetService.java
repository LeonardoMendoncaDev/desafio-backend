package digital.b2w.ri.rest.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import digital.b2w.ri.rest.client.PlanetDTO;
import digital.b2w.ri.rest.client.SwapiClient;
import digital.b2w.ri.rest.models.Planet;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CompletionStage;

import static com.mongodb.client.model.Filters.eq;

/**
 * Criado por Leonardo Mendonça em 10/02/2020.
 */
@ApplicationScoped
public class PlanetService implements Serializable {

    private static final long serialVersionUID = 1211457225114457084L;

    @Inject
    MongoClient mongoClient;

    @Inject
    SwapiClient swapiClient;

    Integer totalAppearsPlanet = 0;

    public Set<Planet> getAll() {
        MongoCursor<Document> cursor = getCollection()
                .find()
                .iterator();
        return planetListBuilder(cursor);
    }

    public Set<Planet> getByName(String name) {
        MongoCursor<Document> cursor = getCollection()
                .find(eq("name", name)).iterator();
        return planetListBuilder(cursor);
    }

    public Set<Planet> getById(String id) {
        MongoCursor<Document> cursor = getCollection()
                .find(new Document("_id", new ObjectId(id))).iterator();
        return planetListBuilder(cursor);
    }

    public void add(Planet planet) {
        this.getAppearsByPlanetName(planet.getName()).thenApplyAsync(result -> {
            Document document = new Document()
                    .append("name", planet.getName())
                    .append("climate", planet.getClimate())
                    .append("terrain", planet.getTerrain())
                    .append("appears", result.intValue());
            getCollection().insertOne(document);
            return document;
        });
    }

    private CompletionStage<Integer> getAppearsByPlanetName(String name) {
        totalAppearsPlanet = 0;
        CompletionStage<JsonObject> json = swapiClient.getPlanetByName(name);
        return json.thenApplyAsync(itemObj -> {
            itemObj.stream().filter(itemArray -> itemArray.getKey().equals("results")).forEach(docObj -> {
                JsonArray docArray = (JsonArray) docObj.getValue();
                docArray.stream().forEach(resultsObj -> {
                    JsonObject resultsDoc = (JsonObject) resultsObj;
                    resultsDoc.stream().filter(resultsArray -> resultsArray.getKey().equals("films")).forEach(films -> {
                        JsonArray filmsArray = (JsonArray) films.getValue();
                        totalAppearsPlanet = filmsArray.size();
                    });
                });
            });
            return totalAppearsPlanet;
        });
    }

    public void remove(String id) {
        getCollection()
                .deleteOne(new Document("_id", new ObjectId(id)));
    }

    private MongoCollection getCollection() {
        return mongoClient
                .getDatabase("planets")
                .getCollection("planet");
    }

    private Set<Planet> planetListBuilder(MongoCursor<Document> cursor) {
        Set<Planet> planets = Collections.synchronizedSet(new LinkedHashSet<>());
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Planet planet = new Planet();
                planet.setId(document.getObjectId("_id").toString());
                planet.setName(document.getString("name"));
                planet.setClimate(document.getString("climate"));
                planet.setTerrain(document.getString("terrain"));
                if(document.get("appears") != null) {
                    planet.setAppears(document.get("appears").toString());
                }
                planets.add(planet);
            }
        } finally {
            cursor.close();
        }
        return planets;
    }

}
