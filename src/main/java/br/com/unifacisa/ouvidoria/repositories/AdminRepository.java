package br.com.unifacisa.ouvidoria.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.unifacisa.ouvidoria.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, String>{
    
}
