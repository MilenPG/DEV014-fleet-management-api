package api.fleetManagementAPI.repositories;

import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //List<User> findAllUsers(Pageable page);
}
