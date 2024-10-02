package ge.croco.customernotificationsystem.service.impl;

import ge.croco.customernotificationsystem.entity.User;
import ge.croco.customernotificationsystem.model.UserDTO;
import ge.croco.customernotificationsystem.repo.UserRepository;
import ge.croco.customernotificationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);
    }

    @Override
    public boolean userExists(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }
}
