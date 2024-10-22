package com.springboot.exercise3.service;

import com.springboot.exercise3.model.Contact;
import com.springboot.exercise3.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id).orElseThrow();
        contact.setName(contactDetails.getName());
        contact.setAddress(contactDetails.getAddress());
        contact.setPhone(contactDetails.getPhone());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public Contact getContact(Long id) {
        return contactRepository.findById(id).orElse(null);
    }



}
