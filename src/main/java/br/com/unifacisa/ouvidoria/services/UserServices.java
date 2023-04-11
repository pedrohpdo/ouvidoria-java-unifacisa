package br.com.unifacisa.ouvidoria.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import br.com.unifacisa.ouvidoria.entities.Feedback;
import br.com.unifacisa.ouvidoria.entities.User;
import br.com.unifacisa.ouvidoria.enums.FeedbackType;
import br.com.unifacisa.ouvidoria.repositories.UserRepository;

/**
 * @author Pedro Henrique Pereira
 * 
 *         Class which referees to te user services on the database
 */

@Service
public class UserServices {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackService fbService;
    
    /**
     * 
     * Add a new user on User's database if they don't exists
     * 
     * @param name     String new user's complete name
     * @param email    String new user's email -> gonna be the primary key
     * @param password String new user's password
     */
    public String saveUser(String name, String email, String password) {
        try {
            User newUser = new User(name, email, password);
            
            if (userRepository.existsById(email)) {
                return "This User already exists in the system";
            }

            userRepository.save(newUser);
            return "User created successfully";

        } catch (IllegalArgumentException e) {
            return "Error to create new User. Some data are invalid or missing";
        }

    }  

     /**
     * Check if the user already exists on User's database
     * 
     * @param email    String user's email
     * @param password String user's password
     */
    public User checkUser(String email, String password) {
        try {
            User userToCheck = userRepository.findById(email).get();

            if (userToCheck != null && userToCheck.getPassword().equals(password)) { return userToCheck; }
            
        } catch (NoSuchElementException e) {
            // Wrong mail or password

        } catch (InvalidDataAccessApiUsageException e) {
            System.err.println("Não foi possivel verificar se o email ou senha estão corretos");

        } catch (NullPointerException e) {
            System.err.println("nao digita valor nulo carai");
        }
        return null;
    }

    /**
     * 
     * Save a new feedback on the Manifest Database
     * 
     * @param description String feedback's description
     * @param type        String feedback type(claims, compliments, suggestions)
     * @param owner       User feedback author
     * @return A sucessfully message if feedback added or the opposite if catchs any
     *         error
     */
    public String saveNewFeedback(String description, String type, User owner) {
        return fbService.addNewFeedback(description, type, owner);

    }

    /**
     * 
     * Get all feedbacks of one User if he exists on the User Database
     * 
     * @param emailFilter String user email
     */
    public String getFeedbacks(String emailFilter) {
        try {
            User user = userRepository.findById(emailFilter).get();
            if (user.getFeedbacks().isEmpty()) { return "No feedbacks founded"; }
            
            String result = "";
    
            for (Feedback fb : user.getFeedbacks()) {
                result += fb.toString() + System.lineSeparator();
            }
            return result;
            
        } catch (NoSuchElementException e) {
            return "Cannot Find This user email";
        }
    }
}
