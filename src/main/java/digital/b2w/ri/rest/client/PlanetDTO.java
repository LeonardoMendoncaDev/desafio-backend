package digital.b2w.ri.rest.client;

import java.io.Serializable;
import java.util.List;

/**
 * Criado por Leonardo Mendonça em 18/02/2020.
 */
public class PlanetDTO implements Serializable {

    private static final long serialVersionUID = 8420390121469838925L;

    private String id;
    private String name;
    private String terrain;
    private String climate;
    private String appears;

    public PlanetDTO(String id, String name, String climate, String terrain, String appears) {
        this.id = id;
        this.name = name;
        this.terrain = terrain;
        this.climate = climate;
        this.appears = appears;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getAppears() {
        return appears;
    }

    public void setAppears(String appears) {
        this.appears = appears;
    }
}
