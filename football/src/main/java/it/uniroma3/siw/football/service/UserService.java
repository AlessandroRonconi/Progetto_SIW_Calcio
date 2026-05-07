package it.uniroma3.siw.football.service;

import it.uniroma3.siw.football.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.football.exception.UserNotFoundException;
import it.uniroma3.siw.football.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Transactional
    public User save(User u){
        return this.userRepository.save(u);
    }
}
