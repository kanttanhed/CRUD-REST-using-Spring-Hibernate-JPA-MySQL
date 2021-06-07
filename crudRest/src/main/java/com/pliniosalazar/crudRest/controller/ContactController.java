package com.pliniosalazar.crudRest.controller;

import com.pliniosalazar.crudRest.repository.ContactRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }

    // CRUD methods here
}
