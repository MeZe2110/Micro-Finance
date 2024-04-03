package tn.esprit.fundsphere.Repositories.CreditRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit,Integer> {
    List<Credit> findByState(int i);

    Credit findByTranches_IdTranche(Long idTranche);


}
