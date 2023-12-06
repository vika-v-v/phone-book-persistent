import javax.persistence.*;
import java.util.*;

public class GUI {
    public static void main(String[] args) {
        
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("myPersistenceUnit")
                .createEntityManager();
        /*
        entityManager.getTransaction().begin();

        // Create and persist an entity
        PhoneBookEntry entry = new PhoneBookEntry("Vika", "+380111111", false);
        // set properties of 'entity'
        entityManager.persist(entry);

        // Commit transaction
        entityManager.getTransaction().commit();

        // Close the EntityManager
        entityManager.close();
        */
        TypedQuery<PhoneBookEntry> query = entityManager.createQuery("SELECT e FROM PhoneBookEntry e", PhoneBookEntry.class);
        List<PhoneBookEntry> entries = query.getResultList();
        entries.stream().forEach(System.out::println);

    }
}