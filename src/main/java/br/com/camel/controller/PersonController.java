package br.com.camel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.camel.model.Person;
import br.com.camel.repository.PersonRepository;

public class PersonController {
    
    @Autowired
    private PersonRepository repository;
    
    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(repository.findAll());
    }
  
  
    @PostMapping
    public ResponseEntity<?> post(@ResquestBody Person person){
        return ResponseEntity.ok(repository.save(person));
    }
  
    @PutMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @ResquestBody Person person){
        var personOp = repository.findById(id);
        
        return personOp.map( p -> {
            p.setName(person.getName());
            p.setAge(person.getAge());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
        
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
