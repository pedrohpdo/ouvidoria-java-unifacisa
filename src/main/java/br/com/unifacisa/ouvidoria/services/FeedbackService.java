package br.com.unifacisa.ouvidoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.unifacisa.ouvidoria.entities.Feedback;
import br.com.unifacisa.ouvidoria.entities.User;
import br.com.unifacisa.ouvidoria.repositories.FeedbackRepository;

@Service
public class FeedbackService {
    
    @Autowired
    FeedbackRepository feedbackRepository;

    public String addNewFeedback(String description, String type, User owner){
        try {
            Feedback newFeedback = new Feedback(description, type, owner);
            feedbackRepository.save(newFeedback);
            return "Feedback created successfully";

        } catch (IllegalArgumentException e) {
           // System.err.println("Error to save new feedback. Some data are invalid or missing");
            return "Error to save new feedback. Some data are invalid or missing";

        } catch (DataIntegrityViolationException e){
            return "A mensagem é muito grande. Limite: 255 caracteres"; 
        }
    }

    public String deleteFeedbackById(long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return "Feedback deleted successfully";
        }

        return "Feedback cannot be founded";

    }
}
