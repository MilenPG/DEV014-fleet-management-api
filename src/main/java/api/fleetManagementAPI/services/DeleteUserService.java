package api.fleetManagementAPI.services;

import api.fleetManagementAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<HttpStatus> deleteUserById(Integer uid) {
        try {
            userRepository.deleteById(uid);
            System.out.println("Usuario ID: "+uid+ " eliminado");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
