package it.uniroma3.siw.football.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.UserNotFoundException;
import it.uniroma3.siw.football.model.User;
import it.uniroma3.siw.football.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly=true)
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
}
