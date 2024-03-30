package tn.esprit.fundsphere.Services.Loan;

import tn.esprit.fundsphere.Entities.LoanManagment.Loan;

import java.util.List;

public interface LoanService {
    Loan AddLoan(Loan loan);

    Loan UpdateLoan(Loan loan);


    List<Loan> ShowAllLoans();

    Loan ShowLoan(Integer id);


    void DeleteLoan(Integer id);

    Loan loan(Long idUser, Float amount, Integer investmentPeriodInMonths);



    void checkloan();
}
