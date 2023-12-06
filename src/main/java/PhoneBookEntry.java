import javax.persistence.*;

@Entity
public class PhoneBookEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private boolean isBusiness;

    public PhoneBookEntry() {
        // This is needed by Hibernate
    }

    public PhoneBookEntry(String name, String phoneNumber, boolean isBusiness) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isBusiness = isBusiness;
    }

    public String toString() {
        return "Phone Number of " + name + " : "  + phoneNumber;
    }
}
