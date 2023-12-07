import javax.persistence.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.BorderLayout;

import java.util.*;

public class GUI {

    private static PhoneBookTableModel model = new PhoneBookTableModel();

    public static void main(String[] args) {

        // Create table
        JFrame frame = new JFrame("Phone Book Table Example");
        
        // Add data from the database
        getData().stream().forEach(entry -> model.addPhoneBookEntry(entry));

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Add table to center
        frame.getContentPane().add(createButtonAddEntry(), BorderLayout.SOUTH); // Add button to south

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static List<PhoneBookEntry> getData() {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("myPersistenceUnit")
                .createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<PhoneBookEntry> query = entityManager.createQuery("SELECT e FROM PhoneBookEntry e", PhoneBookEntry.class);
        List<PhoneBookEntry> entries = query.getResultList();
        
        entityManager.getTransaction().commit();
        entityManager.close();

        return entries;
    }

    private static JButton createButtonAddEntry() {
        JButton button = new JButton("Add entry");
        button.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField phoneNumberField = new JTextField();
            JCheckBox isBusinessCheckBox = new JCheckBox();

            Object[] message = {
                "Name:", nameField,
                "Phone Number:", phoneNumberField,
                "Is Business:", isBusinessCheckBox
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Add New Entry", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                boolean isBusiness = isBusinessCheckBox.isSelected();

                PhoneBookEntry entry = new PhoneBookEntry(name, phoneNumber, isBusiness);
                persistEntry(entry);
                model.addPhoneBookEntry(entry);
            }
        });

        return button;
    }

    private static void persistEntry(PhoneBookEntry entry) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("myPersistenceUnit")
                .createEntityManager();

        entityManager.getTransaction().begin();

        // set properties of 'entity'
        entityManager.persist(entry);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}