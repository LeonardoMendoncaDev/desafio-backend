package digital.b2w.ri.rest.exception;

import java.io.Serializable;

/**
 * Criado por Leonardo Mendonça em 16/02/2020.
 */
public class EmptyPlanet extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5091882297733959993L;

    public EmptyPlanet() {
        super("The Planet Name is necessary!");
    }

}
