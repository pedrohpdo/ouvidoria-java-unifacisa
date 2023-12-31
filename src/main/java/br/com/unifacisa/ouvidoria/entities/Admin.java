package br.com.unifacisa.ouvidoria.entities;

import jakarta.persistence.Entity;

/**
 * Represents an admin on the system
 * 
 * @author Pedro Henrique Pereira
 */
@Entity
public class Admin extends Person {

    public Admin(){}
    
    public Admin(String name, String email, String password){
        super(name, email, password);
        if (!email.contains("@admin.com")) {
            throw new IllegalArgumentException();
        }
        
    }
    
}
