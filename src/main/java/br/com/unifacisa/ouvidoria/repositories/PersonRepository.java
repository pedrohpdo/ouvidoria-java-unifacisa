package br.com.unifacisa.ouvidoria.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.unifacisa.ouvidoria.entities.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

}
