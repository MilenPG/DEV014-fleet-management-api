package api.fleetManagementAPI.controllers;

import api.fleetManagementAPI.models.User;
import api.fleetManagementAPI.services.CreateUserService;
import api.fleetManagementAPI.services.DeleteUserService;
import api.fleetManagementAPI.services.ListUsersService;
import api.fleetManagementAPI.services.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserService createUserService;
    @Autowired
    private ListUsersService listUsersService;
    @Autowired
    private UpdateUserService updateUserService;
    @Autowired
    private DeleteUserService deleteUserService;


    @PostMapping
    public User createUser(@RequestBody User user) // doc swagger req body: "name","email","password"
    {
        return createUserService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false, defaultValue="0") Integer page,
                                @RequestParam(required = false, defaultValue="10") Integer limit)
    {
        return listUsersService.getAllUsers(page, limit);
    }

    @PatchMapping(path = "/{uid}")
    public User updateUser(@PathVariable("uid") Integer uid,
                            @RequestBody User user)
    {
        return updateUserService.updateUserById(user, uid);
    }

    @DeleteMapping(path = "/{uid}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("uid") Integer uid)
    {
        return deleteUserService.deleteUserById(uid);
    }

}
