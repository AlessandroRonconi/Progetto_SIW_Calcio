package it.uniroma3.siw.football.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.football.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
