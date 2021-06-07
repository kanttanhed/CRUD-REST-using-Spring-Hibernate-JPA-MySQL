package com.pliniosalazar.crudRest.controller;

import java.util.List;

import com.pliniosalazar.crudRest.model.Contact;
import com.pliniosalazar.crudRest.repository.ContactRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return repository.findById(id)
          .map(record -> ResponseEntity.ok().body(record))
          .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return repository.save(contact);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id,
                                        @RequestBody Contact contact){
        return repository.findById(id)
            .map(record -> {
                record.setName(contact.getName());
                record.setEmail(contact.getEmail());
                record.setPhone(contact.getPhone());
                Contact updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
             }).orElse(ResponseEntity.notFound().build());
  }

    
}
