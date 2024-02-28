package tn.esprit.fundsphere.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;

public interface IInvestmentRepository extends JpaRepository<Investment,Integer> {
}
