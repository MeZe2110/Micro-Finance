package tn.esprit.fundsphere.Repositories.LoanRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;

import java.util.Date;
import java.util.List;

public interface ILoanRepository extends JpaRepository<Loan,Integer> {
    List<Loan> findByStatusFalse();
    List<Loan> findByDateFinBefore(Date date);
}
