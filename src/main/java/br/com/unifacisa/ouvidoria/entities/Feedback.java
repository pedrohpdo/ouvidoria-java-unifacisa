package br.com.unifacisa.ouvidoria.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Abstract representation of a manifestation formulated by a user
 * 
 * @author Pedro Henrique Pereira
 */

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    private User owner;

    public Feedback() {
    }

    public Feedback(String description, String type, User owner) {
        if (description == null || type == null || owner == null) {
            throw new IllegalArgumentException("Cannot have any parameter null");
        }

        this.type = type;
        this.description = description;
        this.owner = owner;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public String toString() {
        return String.format("ID: %d | Tipo: %s | Descrição: %s ", this.id, this.type, this.description);
    }

}
