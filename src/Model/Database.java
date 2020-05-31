package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:./MyContacts.db";
    private static volatile Database instance = null;
    private final List<Contact> data;
    private Connection cnn = null;

    private Database() {
        if (instance != null)
            throw new RuntimeException("Use getInstance method!");
        data = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null)
                    instance = new Database();
            }
        }
        return instance;
    }

    public void connect() throws SQLException, ClassNotFoundException {
        if (cnn == null) {
            Class.forName("org.sqlite.JDBC");
            cnn = DriverManager.getConnection(DB_URL);
            createDB();
        }
    }

    public void disconnect() throws SQLException {
        if (cnn != null)
            cnn.close();
    }

    private void createDB() throws SQLException {
        if (cnn == null) return;
        try (Statement stm = cnn.createStatement()) {
            String query = "create table if not exists Contact" +
                    "(FirstName text," +
                    "LastName text," +
                    "Phone text not null ," +
                    "Notes text )";
            stm.execute(query);
        }
    }

    public void loadDB() throws SQLException {
        if (cnn == null) return;
        data.clear();

        try (Statement stm = cnn.createStatement()) {
            String query = "select [FirstName], [LastName], [Phone], [Notes] from Contact";
            try (ResultSet resultSet = stm.executeQuery(query)) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString(1);
                    String lastName = resultSet.getString(2);
                    String phone = resultSet.getString(3);
                    String notes = resultSet.getString(4);
                    data.add(new Contact(firstName, lastName, phone, notes));
                }
            }
        }
    }

    public void addContact(Contact object) throws SQLException {
        data.add(object);
        saveToDB(object);
    }

    public void deleteContact(Contact object) throws SQLException {
        data.removeIf(o -> o.equals(object));
        deleteInDB(object);
    }

    private void saveToDB(Contact object) throws SQLException {
        if (cnn == null) return;
        String query = "insert into Contact([FirstName], [LastName], [Phone], [Notes]) values (?,?,?,?)";
        ExecuteQuery(object, query);
    }

    private void deleteInDB(Contact object) throws SQLException {
        if (cnn == null) return;
        String query = "delete from Contact where [FirstName] = ? and [LastName] = ? and [Phone] = ? and [Notes] = ?";
        ExecuteQuery(object, query);
    }

    private void ExecuteQuery(Contact object, String query) throws SQLException {
        try (PreparedStatement stm = cnn.prepareStatement(query)) {
            int index = 1;
            stm.setString(index++, object.getFirstName());
            stm.setString(index++, object.getLastName());
            stm.setString(index++, object.getPhone());
            stm.setString(index, object.getNotes());
            stm.executeUpdate();
        }
    }

    public List<Contact> getData() {
        return Collections.unmodifiableList(data);
    }
}
