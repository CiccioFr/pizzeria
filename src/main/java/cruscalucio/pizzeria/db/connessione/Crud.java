package cruscalucio.pizzeria.db.connessione;

import static cruscalucio.pizzeria.db.connessione.Connessione.dbPersistence;
import interfacce.IPrimaryKey;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Property;

/**
 *
 * @author Francesco
 */
public class Crud<T extends IPrimaryKey> {

    private final Class<T> beanCrud;

    public Crud(Class<T> clazz) {
        this.beanCrud = clazz;
    }

    public void salva(T entity) {
        EntityManager db = dbPersistence();
        EntityTransaction tx = db.getTransaction();

        try {
            tx.begin();
            if (entity.getId() != null) {
                db.persist(entity);
            } else {
                T stored = (T) db.find(beanCrud, entity.getId());
                PropertyUtils.copyProperties(stored, entity);
                db.merge(stored);
            }
            tx.commit();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            db.close();
        }
    }

    public T leggi(Integer id) {
        EntityManager db = dbPersistence();
        try {
            return db.find(beanCrud, id);
        } finally {
            db.close();
        }
    }

    public List<T> leggiTutti() {
        String nameDB = beanCrud.getName() + ".findAll";
        EntityManager db = dbPersistence();
        try {
            TypedQuery<T> q = db.createNamedQuery(nameDB, beanCrud);
            return q.getResultList();
        } finally {
            db.close();
        }
    }

    public void elimina(T entity) {
        EntityManager db = dbPersistence();
        EntityTransaction tx = db.getTransaction();
        try {
            tx.begin();
            T daEliminare = db.find(beanCrud, entity.getId());
            db.remove(daEliminare);
            db.flush(); // opzionale e qui inutile grazie alla commit
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            db.close();
        } finally {
        }
    }
}
