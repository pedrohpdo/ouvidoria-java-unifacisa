package br.com.unifacisa.ouvidoria.services;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import br.com.unifacisa.ouvidoria.entities.Admin;
import br.com.unifacisa.ouvidoria.entities.Feedback;
import br.com.unifacisa.ouvidoria.entities.User;
// import br.com.unifacisa.ouvidoria.models.AdminController;
import br.com.unifacisa.ouvidoria.repositories.AdminRepository;
import br.com.unifacisa.ouvidoria.repositories.UserRepository;

/**
 * Class which referees to te Admin services on the database
 * 
 * @author Pedro Henrique Pereira
 */
@Service
public class AdminServices {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackService fbService;

    @Autowired
    UserServices userServices;

    /**
     * 
     * Add a new Admin on Admin's database if they don't exists
     * 
     * @param name     String new Admin's complete name
     * @param email    String new Admin's email -> gonna be he's primary key
     * @param password String new Admin's password
     */
    public String saveAdmin(String name, String email, String password) {
        try {
            Admin newAdmin = new Admin(name, email, password);

            if (adminRepository.existsById(email)) {
                return "This Admin already exists in the system";

            } else {
                adminRepository.save(newAdmin);
            }

        } catch (IllegalArgumentException e) {
            return "Error to create new Admin. Some data are invalid or missing";
        }

        return "Admin saved successfully";
    }

    /**
     * Check if the Admin already exists on Admin's database
     * 
     * @param email    String admin's email
     * @param password String admin's password
     */
    public Admin checkAdmin(String email, String password) {
        try {
            Admin admin = adminRepository.findById(email).get();

            if (admin != null && admin.getPassword().equals(password)) {

                return admin;
            }

        } catch (NoSuchElementException e) {
            // System.err.println("vc digitou um email ou senha err");

        } catch (InvalidDataAccessApiUsageException e) {
            System.err.println("Não foi possivel verificar se o email ou senha estão corretos");

        } catch (NullPointerException e) {
            System.err.println("nao digita valor nulo carai");
        }

        return null;
    }

    /**
     * 
     * @param usermail
     * @return
     */
    public String getUserFeedback(String usermail) {
        return userServices.getFeedbacks(usermail);
    }

    /**
     * Get all the feedbacks of all users on the database
     * 
     * @return all feedbacks of all users
     */
    public String getAllFeedbacks() {
        String result = "";
        
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();

        if (users.isEmpty()) {
            result += "NO USERS ON SYSTEM";
            return result;
        }

        for (User u : users ) {
            result += "Aluno: " + u.getName() + System.lineSeparator();

            if (u.getFeedbacks().isEmpty()) {
                result += "Nenhum feedback cadastrado" + System.lineSeparator();

            } else {
                for (Feedback f : u.getFeedbacks()) {
                    result += f.toString() + System.lineSeparator();
                }
            }
            result += System.lineSeparator();
        }

        return result;
    }

    /**
     * Delete one feedback on the Manifest database by Id
     * 
     * @param id Long feedback id
     * @return a message confirming the delete operation
     */
    public String deleteFeedbackByID(long id) {
        
        return fbService.deleteFeedbackById(id);
    }

    public String getUsers() {
        String result = "";
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        
        if (users.isEmpty()) { return "No User data on System"; }

        for (int i = 0; i < users.size(); i++) {
            result += String.format("%d : %s", i, users.get(i).toString() + System.lineSeparator());
        }
        
        return result;
    }

    /**
     * Delete a user on the database and consequently delete all their feedbacks
     * 
     * @param emailFilter String user email who gonna be deleted
     */
    public String deleteUserByEmail(String emailFilter) {

        try {
            User userToDelete = userRepository.findById(emailFilter).get();
            userRepository.delete(userToDelete);
            return "User delete sucessfully";


        } catch (NoSuchElementException e) {
            return "This user cannot be founded";
        }
    }

}
