package ge.croco.customernotificationsystem.service;

import ge.croco.customernotificationsystem.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserDTO user);
    boolean userExists(String userName);
}
