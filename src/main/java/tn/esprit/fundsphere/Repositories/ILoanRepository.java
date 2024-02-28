package tn.esprit.fundsphere.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;

public interface ILoanRepository extends JpaRepository<Loan,Integer> {
}
