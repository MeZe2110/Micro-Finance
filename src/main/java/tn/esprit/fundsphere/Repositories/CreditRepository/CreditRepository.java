package tn.esprit.fundsphere.Repositories.CreditRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entites.CrediMangment.Credit;

public interface CreditRepository extends JpaRepository<Credit,Long> {
}
