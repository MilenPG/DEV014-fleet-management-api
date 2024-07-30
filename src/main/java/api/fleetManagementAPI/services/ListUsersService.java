package api.fleetManagementAPI.services;

import api.fleetManagementAPI.models.User;
import api.fleetManagementAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListUsersService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(Integer pageNumber,
                                  Integer limit)
    {
        Pageable page = PageRequest.of(pageNumber, limit);

        return userRepository.findAll(page).getContent();
    }
}
