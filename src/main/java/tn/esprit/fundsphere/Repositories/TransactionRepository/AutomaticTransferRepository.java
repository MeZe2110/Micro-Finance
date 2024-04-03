package tn.esprit.fundsphere.Repositories.TransactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.TransactionManagment.AutomaticTransfer;

public interface AutomaticTransferRepository extends JpaRepository<AutomaticTransfer, Long> {
}
