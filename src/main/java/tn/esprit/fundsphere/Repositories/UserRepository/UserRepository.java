package tn.esprit.fundsphere.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String Username);
    boolean existsByUsername(String username);
}
