package br.com.unifacisa.ouvidoria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unifacisa.ouvidoria.entities.Person;
import br.com.unifacisa.ouvidoria.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public Person login(String usermail, String userpass) {
		Optional<Person> userLogged = personRepository.findById(usermail);
		
		if (!userLogged.isPresent()) {
			throw new NullPointerException("No user founded");
		}
		
		if (userLogged.get().getPassword().equalsIgnoreCase(userpass)) {
			return userLogged.get();			
		}
		
		return null;
		
	}
}
