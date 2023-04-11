package br.com.unifacisa.ouvidoria.repositories;
import org.springframework.data.repository.CrudRepository;

import br.com.unifacisa.ouvidoria.entities.User;

public interface UserRepository extends CrudRepository<User, String> {
}
