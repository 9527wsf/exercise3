package exercise3_2.client;

import exercise3_2.model.Contact;
import exercise3_2.service.ContactService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class ContactManagerUI {
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JButton addButton;
    private JButton viewButton;
    private JTextField searchIdField;
    private JButton updateButton;
    private JTextField updateIdField;
    private JTextField deleteIdField;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextArea displayArea;

    public ContactManagerUI() {
        JFrame frame = new JFrame("个人通讯录系统");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idField = new JTextField(10);
        nameField = new JTextField(20);
        addressField = new JTextField(20);
        phoneField = new JTextField(20);
        deleteIdField = new JTextField(10);
        searchIdField = new JTextField(10);
        updateIdField = new JTextField(10);
        addButton = new JButton("添加联系人");
        viewButton = new JButton("查看所有联系人");
        deleteButton = new JButton("删除联系人");
        searchButton = new JButton("查找联系人");
        updateButton = new JButton("修改联系人");
        displayArea = new JTextArea(20, 30);
        displayArea.setEditable(false);

        // 添加联系人事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                int id = Integer.parseInt(idField.getText());
                ContactService.addContact(id, name, address, phone);
                JOptionPane.showMessageDialog(frame, "联系人已添加");
                clearFields();
            }
        });

        // 查找联系人事件
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(searchIdField.getText());
                    Contact contact = ContactService.getContact(id);
                    if (contact != null) {
                        displayArea.setText("ID: " + contact.getId() + " " + contact.getName() + ", " + contact.getAddress() + ", " + contact.getPhone());
                    } else {
                        displayArea.setText("未找到联系人");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "请输入有效的ID");
                }
            }
        });

        // 查看所有联系人事件
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Contact> contacts = ContactService.getAllContacts();
                displayArea.setText(""); // 清空显示区域
                for (Contact contact : contacts) {
                    displayArea.append("ID: " + contact.getId() + contact.getName() + ", " + contact.getAddress() + ", " + contact.getPhone() + "\n");
                }
            }
        });

        // 修改联系人事件
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(updateIdField.getText());
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String phone = phoneField.getText();
                    ContactService.updateContact(id, name, address, phone);
                    JOptionPane.showMessageDialog(frame, "联系人已修改");
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "请输入有效的ID");
                }
            }
        });

        // 删除联系人事件
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(deleteIdField.getText());
                    ContactService.deleteContact(id);
                    JOptionPane.showMessageDialog(frame, "联系人已删除");
                    clearFields(); // 清空输入框
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "请输入有效的ID");
                }
            }
        });


        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("姓名:"));
        panel.add(nameField);
        panel.add(new JLabel("住址:"));
        panel.add(addressField);
        panel.add(new JLabel("电话:"));
        panel.add(phoneField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(new JLabel("查找ID:"));
        panel.add(searchIdField);
        panel.add(searchButton);
        panel.add(new JLabel("删除ID:"));
        panel.add(deleteIdField);
        panel.add(deleteButton);
        panel.add(new JLabel("修改ID:"));
        panel.add(updateIdField);
        panel.add(updateButton);

        panel.add(new JScrollPane(displayArea)); // 用于显示联系人信息

        frame.add(panel);
        frame.setVisible(true);
    }

    // 清空输入框
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        deleteIdField.setText("");
        searchIdField.setText("");
        updateIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactManagerUI());
    }
}