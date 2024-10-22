package exercise3_2.service;

import exercise3_2.dao.ContactDAO;
import exercise3_2.model.Contact;

import java.util.List;

public class ContactService {
    // 添加联系人
    public static void addContact(int id, String name, String address, String phone) {
        Contact contact = new Contact(id,name, address, phone);
        ContactDAO.save(contact);
    }

    // 修改联系人
    public static void updateContact(int id, String name, String address, String phone) {
        Contact contact = new Contact(id, name, address, phone);
        ContactDAO.update(id, contact);
    }

    // 查看联系人
    public static Contact getContact(int id) {
        return ContactDAO.getById(id);
    }

    // 查看所有联系人
    public static List<Contact> getAllContacts() {
        return ContactDAO.getAll();
    }

    // 删除联系人
    public static void deleteContact(int id) {
        ContactDAO.delete(id);
    }
}