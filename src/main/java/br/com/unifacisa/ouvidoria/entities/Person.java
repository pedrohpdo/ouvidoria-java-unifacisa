package br.com.unifacisa.ouvidoria.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Represents a Person who has completed her cadaster in the system. It can be a
 * User or a Admin
 * 
 * @author Pedro Henrique Pereira
 */
 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "status", length = 5, discriminatorType = DiscriminatorType.STRING)
public class Person {
    
    @Id
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String password;
    
	public Person() {}

    public Person(String name, String email, String password) {
        super();
        
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Não é possível validar dados nulos");
        }
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toString() {
    	return String.format("Name: %s | Email: %s", this.name, this.email);
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
