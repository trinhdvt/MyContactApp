package View;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    private JMenuItem addMenuItem, editMenuItem, deleteMenuItem, exitMenuItem;
    private JTable table;
    private JPopupMenu popupMenu;

    public MyView(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        initComponent();

        setVisible(true);
    }

    private void initComponent() {
        createMenuBar();
        createTable();
        createPopupMenu();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Contacts");
        addMenuItem = new JMenuItem("Add");
        editMenuItem = new JMenuItem("Edit");
        deleteMenuItem = new JMenuItem("Delete");
        exitMenuItem = new JMenuItem("Exit");
        menu.add(addMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void createTable() {
        table = new JTable();
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
    }

    public JTable getTable() {
        return table;
    }

    public JMenuItem getAddMenuItem() {
        return addMenuItem;
    }

    public JMenuItem getEditMenuItem() {
        return editMenuItem;
    }

    public JMenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
