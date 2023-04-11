package br.com.unifacisa.ouvidoria.forms;

import br.com.unifacisa.ouvidoria.entities.Person;

public class PersonForm {
	public void personForm(String name, String email, String password) {
		  
	}
	
	public void personForm(Person p) {
		
		personForm(p.getName(), p.getEmail(), p.getPassword());
		
	}
}
