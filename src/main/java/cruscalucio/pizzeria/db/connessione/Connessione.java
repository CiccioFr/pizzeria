package cruscalucio.pizzeria.db.connessione;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Francesco
 */
public class Connessione {

    public static EntityManager dbPersistence() {
        return Persistence.createEntityManagerFactory("pizzeria")
                .createEntityManager();
    }
}
