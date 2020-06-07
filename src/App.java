import Controller.MyController;
import Model.ContactTableModel;
import View.MainView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyController(new MainView("MyContactsApp"), new ContactTableModel()));
    }
}