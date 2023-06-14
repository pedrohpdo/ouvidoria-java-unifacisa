package br.com.unifacisa.ouvidoria.entities;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

/**
 * Abstract representation of a User
 * 
 * @author Pedro Henrique Pereira
 */

@Entity
public class User extends Person {
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Cascade({CascadeType.MERGE, CascadeType.REMOVE})
    private List<Feedback> feedbacks;

    public User() {}

    public User(String username, String email, String password) {
        super(username, email, password);
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", this.getName(), this.getEmail());
    }
}
