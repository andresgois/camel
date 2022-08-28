package br.com.camel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.camel.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
