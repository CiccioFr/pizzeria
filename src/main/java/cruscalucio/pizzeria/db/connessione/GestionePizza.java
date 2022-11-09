package cruscalucio.pizzeria.db.connessione;

import cruscalucio.pizzeria.db.Pizza;
import static cruscalucio.pizzeria.db.connessione.Connessione.dbPersistence;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Francesco
 */
public class GestionePizza {

    public static void setPizza(Pizza pizza) {
        EntityManager db = dbPersistence();
        EntityTransaction tx = db.getTransaction();

        try {
            tx.begin();
            if (pizza.getId() == null) {
                db.persist(pizza);
            } else {
                Pizza aggiornaPizza = db.find(pizza.getClass(), pizza.getId());
                aggiornaPizza.setNome(pizza.getNome());
                db.merge(aggiornaPizza);
            }
            tx.commit();
        } catch (Exception e) {
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            db.close();
        }
    }

    public static void getPizza() {
        EntityManager db = dbPersistence();
        try {
            // controlla l'esecuzione delle query digitate.
            // "Pizza.findAll" nome della classe java che genera il bean
            TypedQuery<Pizza> pizz = db.createNamedQuery("Pizza.findAll", Pizza.class);
            List<Pizza> listpizze = pizz.getResultList();
            for (Pizza g : listpizze) {
                System.out.println("Pizza: " + g.getNome());
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public static void deletePizza(Pizza pizza) {
        EntityManager db = dbPersistence();
        EntityTransaction tx = db.getTransaction();

        try {
            tx.begin();
            Pizza daEliminare = db.find(Pizza.class, pizza.getId());
            db.remove(daEliminare);
            db.flush(); // opzionale sincronizza lo stato di esclip o hibernate col database // CONSOLIDA LE MODIFICHE
        } catch (Exception e) {
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            db.close();
        }
    }
}
// sql injection come viene scongiurata?
