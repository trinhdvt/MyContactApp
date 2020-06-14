package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JMenuItem addMenuItem, editMenuItem, deleteMenuItem, exitMenuItem, aboutMenuItem;
    private JTable table;
    private JPopupMenu popupMenu;
    private JButton addBtn, editBtn, deleteBtn, searchBtn;
    private JTextField searchField;
    private JComboBox<String> searchCBB;

    public MainView(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(700, 500);
        setMinimumSize(new Dimension(650, 500));
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

        JMenu contactMenu = new JMenu("Contacts");
        contactMenu.setIcon(new ImageIcon("images\\personal.png"));

        addMenuItem = new JMenuItem("Add");
        addMenuItem.setIcon(new ImageIcon("images\\btn_add.png"));
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setIcon(new ImageIcon("images\\logout.png"));
        contactMenu.add(addMenuItem);
        contactMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(contactMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
    }

    private void createTable() {
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();

        editMenuItem = new JMenuItem("Edit");
        editMenuItem.setIcon(new ImageIcon("images\\edit.png"));
        deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.setIcon(new ImageIcon("images\\btn_delete.png"));

        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
    }

    private void createTitle() {
        JLabel label = new JLabel("My Contacts");
        label.setIcon(new ImageIcon("images\\person-48.png"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        this.getContentPane().add(label, BorderLayout.NORTH);
    }

    private void createBtnPanel() {
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addBtn = new JButton("Add");
        addBtn.setIcon(new ImageIcon("images\\btn_add.png"));
        editBtn = new JButton("Edit");
        editBtn.setIcon(new ImageIcon("images\\edit.png"));
        deleteBtn = new JButton("Delete");
        deleteBtn.setIcon(new ImageIcon("images\\btn_delete.png"));
        searchBtn = new JButton("Search");
        searchBtn.setIcon(new ImageIcon("images\\btn_search.png"));
        searchField = new JTextField(10);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchCBB = new JComboBox<>();
        searchCBB.addItem("FirstName");
        searchCBB.addItem("LastName");
        searchCBB.addItem("Phone");
        searchCBB.addItem("Notes");
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(searchField);
        btnPanel.add(searchCBB);

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

    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
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

    public JComboBox<String> getSearchCBB() {
        return searchCBB;
    }
}
