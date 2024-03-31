package tn.esprit.fundsphere.Repositories.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String Username);
}
