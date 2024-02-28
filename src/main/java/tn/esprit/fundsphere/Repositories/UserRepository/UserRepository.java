package tn.esprit.fundsphere.Repositories.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.UserManagment.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
