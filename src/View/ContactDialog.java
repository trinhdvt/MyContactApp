package View;

import javax.swing.*;
import java.awt.*;

public class ContactDialog extends JDialog {
    private JTextField firstNameField, lastNameField, phoneField, notesField;
    private JButton okBtn, cancelBtn;
    private JPanel centerPanel;

    public ContactDialog(JFrame parent) {
        super(parent);
        setSize(300, 204);
        setResizable(false);
        setLocationRelativeTo(parent);

        initComponent();
    }

    private void initComponent() {
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        phoneField = new JTextField();
        notesField = new JTextField();

        okBtn = new JButton("OK");
        okBtn.setIcon(new ImageIcon("images\\Select.png"));
        cancelBtn = new JButton("Cancel");
        cancelBtn.setIcon(new ImageIcon("images\\close.png"));

        centerPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.add(new JLabel("FirstName:"));
        centerPanel.add(firstNameField);
        centerPanel.add(new JLabel("LastName"));
        centerPanel.add(lastNameField);
        centerPanel.add(new JLabel("Phone"));
        centerPanel.add(phoneField);
        centerPanel.add(new JLabel("Notes"));
        centerPanel.add(notesField);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);

        this.setLayout(new BorderLayout(5, 5));
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getNotesField() {
        return notesField;
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }
}
