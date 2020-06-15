package Controller;

import Model.Contact;
import Model.ContactTableModel;
import Model.Database;
import View.ContactDialog;
import View.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MyController {
    private final MainView view;
    private final ContactTableModel model;
    private final Database db = Database.getInstance();
    private final ContactDialog dialog;
    private Contact editContact = null;

    public MyController(MainView view, ContactTableModel model) {
        this.view = view;
        this.model = model;
        this.dialog = new ContactDialog(view);
        initView();
        initModel();
        initController();
        view.setVisible(true);
    }

    private void initView() {
        view.getTable().setModel(model);
    }

    private void initModel() {
        model.setData(db.getData());
    }

    private void initController() {
        initWindowsActions();
        initDialogActions();
        initBtnActions();
        initMenuActions();
        initPopupMenuActions();
    }

    private void initBtnActions() {
        view.getAddBtn().addActionListener(addEventListener());
        view.getEditBtn().addActionListener(editEventListener());
        view.getDeleteBtn().addActionListener(deleteEventListener());
        view.getSearchBtn().addActionListener(searchEventListener());
    }

    private void initWindowsActions() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    try {
                        db.connect();
                        db.loadDB();
                        model.fireTableDataChanged();
                    } catch (SQLException | ClassNotFoundException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(),
                                "Error when loading!", JOptionPane.ERROR_MESSAGE);
                        view.dispose();
                    }
                }).start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(view, "Do you want to exit?",
                        "Exit confirm", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    try {
                        db.disconnect();
                    } catch (SQLException ignored) {
                    } finally {
                        view.dispose();
                    }
                }
            }
        });
    }

    private void initDialogActions() {
        dialog.getOkBtn().addActionListener(l -> {
            String firstName = dialog.getFirstNameField().getText().trim();
            String lastName = dialog.getLastNameField().getText().trim();
            String phone = dialog.getPhoneField().getText().trim();
            String notes = dialog.getNotesField().getText().trim();
            if (phone.equals(""))
                JOptionPane.showMessageDialog(dialog, "Phone number cannot be null",
                        "Invalid value", JOptionPane.ERROR_MESSAGE);
            else if (!phone.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(dialog, "Phone must be a number",
                        "Invalid value", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    if (editContact != null) {
                        db.deleteContact(editContact);
                    }
                    db.addContact(new Contact(firstName, lastName, phone, notes));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(dialog, e.getMessage(), "Save contact error!", JOptionPane.ERROR_MESSAGE);
                }
                model.fireTableDataChanged();
                clearDialog();
            }
        });
        dialog.getCancelBtn().addActionListener(l -> clearDialog());
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.getCancelBtn().doClick();
            }
        });
    }

    private void initMenuActions() {
        view.getExitMenuItem().addActionListener(l -> view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)));
        view.getAddMenuItem().addActionListener(addEventListener());
        view.getAboutMenuItem().addActionListener(l -> JOptionPane.showMessageDialog(view,
                "SVTH: Đỗ Văn Trình\n" +
                        "Lớp: 18TCLC-Nhật\n" +
                        "MSSV: 1021280276")
        );
    }

    private void initPopupMenuActions() {
        view.getEditMenuItem().addActionListener(editEventListener());
        view.getDeleteMenuItem().addActionListener(deleteEventListener());
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = view.getTable();
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3)
                    view.getPopupMenu().show(table, e.getX(), e.getY());
            }
        });
    }

    private void clearDialog() {
        for (Component component : dialog.getCenterPanel().getComponents())
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        editContact = null;
        dialog.dispose();
    }

    private ActionListener addEventListener() {
        return e -> {
            dialog.setTitle("Add Contact");
            dialog.setVisible(true);
        };
    }

    private ActionListener editEventListener() {
        return e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Select a contact to edit!");
                return;
            }
            dialog.setTitle("Edit Contact");
            editContact = db.getData().get(selectedRow);
            dialog.getFirstNameField().setText(editContact.getFirstName());
            dialog.getLastNameField().setText(editContact.getLastName());
            dialog.getPhoneField().setText(editContact.getPhone());
            dialog.getNotesField().setText(editContact.getNotes());
            dialog.setVisible(true);
        };
    }

    private ActionListener deleteEventListener() {
        return event -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Select a contact to delete!");
                return;
            }
            int res = JOptionPane.showConfirmDialog(view, "Delete this contact", "Delete confirm", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                Contact deleteContact = db.getData().get(selectedRow);
                model.fireTableDataChanged();
                try {
                    db.deleteContact(deleteContact);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(view, e.getMessage());
                }
            }
        };
    }

    private ActionListener searchEventListener() {
        return ev -> {
            String searchField = (String) view.getSearchCBB().getSelectedItem();
            if (searchField == null) return;
            String searchValue = view.getSearchField().getText().trim();
            if (searchValue.equals("")) {
                JOptionPane.showMessageDialog(view, "Search value cannot be null");
                return;
            }
            if (searchField.equals("Phone") && !searchValue.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(view, "Search value must contains only number");
                return;
            }
            view.getTable().getSelectionModel().clearSelection();
            for (int i = 0; i < db.getData().size(); i++) {
                Contact object = db.getData().get(i);
                String value = "";
                if (searchField.equals("FirstName"))
                    value = object.getFirstName();
                if (searchField.equals("LastName"))
                    value = object.getLastName();
                if (searchField.equals("Phone"))
                    value = object.getPhone();
                if (searchField.equals("Notes"))
                    value = object.getNotes();
                if (value.equalsIgnoreCase(searchValue)) {
                    view.getTable().addRowSelectionInterval(i, i);
                }
            }
        };
    }
}

