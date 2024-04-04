package tn.esprit.fundsphere.Services.LoanService;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;

import java.util.List;

public interface ILoanService {
    Loan AddLoan(Loan loan);

    Loan UpdateLoan(Loan loan);

    List<Loan> ShowAllLoans();

    Loan ShowLoan(Integer id);

    void DeleteLoan(Integer id);

    Loan loan(Long account_id, Float amount, Integer investmentPeriodInMonths) throws MessagingException;

    @Scheduled(fixedDelay = 86400000)
    void checkloan() throws MessagingException;
}
