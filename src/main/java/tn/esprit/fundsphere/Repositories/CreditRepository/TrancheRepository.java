package tn.esprit.fundsphere.Repositories.CreditRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entites.CrediMangment.Tranche;

public interface TrancheRepository extends JpaRepository<Tranche,Long> {
}
