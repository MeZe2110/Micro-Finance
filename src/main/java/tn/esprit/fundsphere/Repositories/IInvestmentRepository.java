package tn.esprit.fundsphere.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.fundsphere.Entities.InvestmentManagment.InvestStatus;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Invest_stage;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;

import java.util.List;

public interface IInvestmentRepository extends JpaRepository<Investment,Integer> {
  //  @Query("SELECT i FROM Investment i WHERE i.stage =:stage AND i.status =:status")
    List<Investment> findByStageAndStatus(Invest_stage stage,InvestStatus status);
    List<Investment> findByStatus(InvestStatus status);
}
