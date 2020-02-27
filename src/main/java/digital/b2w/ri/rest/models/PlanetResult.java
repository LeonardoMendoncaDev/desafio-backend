package digital.b2w.ri.rest.models;

import java.io.Serializable;

/**
 * Criado por Leonardo Mendonça em 16/02/2020.
 */
public class PlanetResult implements Serializable {

    private static final long serialVersionUID = 3834262358726258631L;

    private String name;
    private String climate;
    private String terrain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }
}
