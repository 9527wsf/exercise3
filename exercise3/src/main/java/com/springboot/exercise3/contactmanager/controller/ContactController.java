package com.springboot.exercise3.contactmanager.controller;

import com.springboot.exercise3.model.Contact;
import com.springboot.exercise3.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping
    public String getAllContacts(Model model) {
        List<Contact> contacts = service.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contactList"; // 返回联系人列表的视图
    }

    @GetMapping("/{id}")
    public String getContact(@PathVariable Long id, Model model) {
        Contact contact = service.getContact(id);
        model.addAttribute("contact", contact);
        return "viewContacts"; // 返回联系人详情的视图
    }

    @GetMapping("/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "addContact"; // 返回添加联系人的视图
    }

    @PostMapping
    public String addContact(@ModelAttribute Contact contact) {
        service.addContact(contact);
        return "redirect:/contacts"; // 添加后重定向到联系人列表
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = service.getContact(id);
        model.addAttribute("contact", contact);
        return "editContact"; // 返回编辑联系人的视图
    }


    @PostMapping("/update/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        service.updateContact(id, contact);
        return "redirect:/contacts"; // 更新后重定向到联系人列表
    }


    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
        return "redirect:/contacts"; // 删除后重定向到联系人列表
    }
}


