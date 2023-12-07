import javax.swing.table.AbstractTableModel;
import java.util.*;

public class PhoneBookTableModel extends AbstractTableModel {
    
    private List<PhoneBookEntry> phoneBookEntries;
    private String[] columnNames = {"ID", "Name", "Phone Number", "Business"};

    public PhoneBookTableModel() {
        this.phoneBookEntries = new ArrayList<>();
    }

    public void addPhoneBookEntry(PhoneBookEntry entry) {
        phoneBookEntries.add(entry); // todo: use copy constructor
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return phoneBookEntries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PhoneBookEntry entry = phoneBookEntries.get(rowIndex);
        switch (columnIndex) {
            case 0: return entry.getId();
            case 1: return entry.getName();
            case 2: return entry.getPhoneNumber();
            case 3: return entry.isBusiness();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
