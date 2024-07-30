package api.fleetManagementAPI.controllers;

import api.fleetManagementAPI.models.User;
import api.fleetManagementAPI.services.CreateUserService;
import api.fleetManagementAPI.services.ListUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    ListUsersService listUsersService;
    @Autowired
    CreateUserService createUserService;

    @PostMapping
    public User createUser(@RequestBody User user)
    {
        return createUserService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers (@RequestParam(required = false, defaultValue="0") Integer page,
                                @RequestParam(required = false, defaultValue="10") Integer limit)
    {
        return listUsersService.getAllUsers(page, limit);
    }



}
