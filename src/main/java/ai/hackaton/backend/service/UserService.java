package ai.hackaton.backend.service;

import ai.hackaton.backend.entity.User;
import ai.hackaton.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Пользователь с такой почтой уже существует");

        return save(user);
    }

    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не существует"));
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByEmail;
    }

}
