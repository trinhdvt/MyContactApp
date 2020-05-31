package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ContactTableModel extends AbstractTableModel {
    private final String[] colName = new String[]{"FirstName", "LastName", "Phone", "Notes"};
    private List<Contact> data = new ArrayList<>();

    public ContactTableModel() {
    }

    public void setData(List<Contact> data) {
        this.data = data;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact object = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return object.getFirstName();
            case 1:
                return object.getLastName();
            case 2:
                return object.getPhone();
            case 3:
                return object.getNotes();
        }
        return null;
    }
}
