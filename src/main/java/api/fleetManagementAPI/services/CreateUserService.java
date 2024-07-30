package api.fleetManagementAPI.services;

import api.fleetManagementAPI.models.User;
import api.fleetManagementAPI.repositories.UserRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    @Autowired
    UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
