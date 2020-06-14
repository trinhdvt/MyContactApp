import Controller.MyController;
import Model.ContactTableModel;
import View.MainView;

public class MainApp {
    public static void main(String[] args) {
        new MyController(new MainView("MyContactsApp"), new ContactTableModel());
    }
}