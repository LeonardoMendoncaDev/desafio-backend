package digital.b2w.ri.rest.models;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Criado por Leonardo Mendonça em 10/02/2020.
 */
@JsonbPropertyOrder({"id", "name", "climate", "terrain", "appears", "films"})
public class Planet implements Serializable {

    private static final long serialVersionUID = -7900397548103628506L;

    private String _id;
    private String name;
    private String climate;
    private String terrain;
    private String appears;
    private List<String> films;

    public Planet() {
    }

    public Planet(String name, String climate, String terrain, List<String> films) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.films = films;
    }

    @JsonbProperty(value = "id")
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    @JsonbProperty(value = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonbProperty(value = "climate")
    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    @JsonbProperty(value = "terrain")
    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @JsonbProperty(value = "appears")
    public String getAppears() {
        return appears;
    }

    public void setAppears(String appears) {
        this.appears = appears;
    }

    @JsonbProperty(value = "films")
    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return Objects.equals(_id, planet._id) &&
                Objects.equals(name, planet.name) &&
                Objects.equals(climate, planet.climate) &&
                Objects.equals(terrain, planet.terrain) &&
                Objects.equals(appears, planet.appears) &&
                Objects.equals(films, planet.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, climate, terrain, appears, films);
    }
}
