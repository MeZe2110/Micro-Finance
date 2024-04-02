package tn.esprit.fundsphere.Repositories.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.fundsphere.Entities.UserManagment.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    @Query("""
select t from Token t inner join User u on t.user.idUser = u.idUser
where t.user.idUser = :userId and t.loggedOut = false
""")
    List<Token> findAllByTokenByUser(Integer userId);
    Optional<Token> findByToken(String token);

}
