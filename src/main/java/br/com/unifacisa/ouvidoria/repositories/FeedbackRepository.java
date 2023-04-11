package br.com.unifacisa.ouvidoria.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.unifacisa.ouvidoria.entities.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    
}
