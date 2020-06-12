package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JMenuItem addMenuItem, editMenuItem, deleteMenuItem, exitMenuItem;
    private JTable table;
    private JPopupMenu popupMenu;
    private JButton addBtn, editBtn, deleteBtn, searchBtn;
    private JTextField searchField;

    public MainView(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        initComponent();

//        setVisible(true);
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(5, 5));
        createMenuBar();
        createTable();
        createTitle();
        createBtnPanel();
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
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
    }

    private void createTitle() {
        JLabel label = new JLabel("Contacts Manager");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        this.getContentPane().add(label, BorderLayout.NORTH);
    }

    private void createBtnPanel() {
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        searchBtn = new JButton("Search");
        searchField = new JTextField(10);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(searchField);

        this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
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

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JTextField getSearchField() {
        return searchField;
    }
}
