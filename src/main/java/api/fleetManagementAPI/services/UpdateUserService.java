package api.fleetManagementAPI.services;

import api.fleetManagementAPI.models.User;
import api.fleetManagementAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUserById(User user, Integer uid)
    {
        User userById = userRepository.findById(uid).get();
        //if(userById.isPresent()) {
        userById.setName(user.getName());
        userById.setEmail(user.getEmail());
        userById.setPassword(user.getPassword());

        userRepository.save(userById);
        return userById;
    }
}
