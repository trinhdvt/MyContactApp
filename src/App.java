import Controller.MyController;
import Model.ContactTableModel;
import View.MyView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyController(new MyView("MyContactsApp"), new ContactTableModel()));
    }
}