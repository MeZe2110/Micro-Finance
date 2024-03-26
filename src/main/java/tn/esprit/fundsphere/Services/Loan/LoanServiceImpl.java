package tn.esprit.fundsphere.Services.Loan;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Repositories.ILoanRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private ILoanRepository loanRepository;


    @Override
    public Loan AddLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan UpdateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> ShowAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan ShowLoan(Integer id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteLoan(Integer id) {
        loanRepository.deleteById(id);
    }

    @Override
    public Loan loan(Integer idUser, Float amount, Integer investmentPeriodInMonths) {


        Loan loan = new Loan();
        loan.setAmount(amount);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        loan.setDateInv(calendar.getTime());
        calendar.add(Calendar.MONTH, investmentPeriodInMonths);
        Date endDate = calendar.getTime();
        loan.setDateFin(endDate);
        loan.setInvestmentPeriodInMonths(investmentPeriodInMonths);
        Float score_periode = 0.0f;
        Float score_amount = 0.0f;
        if (investmentPeriodInMonths >= 48) { //sup 4 ans
            score_periode = 0.5f;

        } else {
            score_periode = (investmentPeriodInMonths / 48) * 0.5f;
        }
        if (amount >= 50000) {
            score_amount = 0.5f;
        } else {
            score_amount = (amount / 50000) * 0.5f;
        }
        Float score = score_amount + score_periode;
        loan.setScore(score);
        loan.setInterest((amount * score * 6) / 100);
        // Annuel ( par an compose)
        loan.setStatus(false);
        loan.setId_user(idUser);
//SEND transaction mel User l admin, if retrait then tarja3 simple
        loanRepository.save(loan);

        return loan;
    }

    @Override
    public List<Loan> bystatus() {
        return loanRepository.findByStatusFalse();
    }

    @Scheduled(fixedDelay = 86400000)

    @Override
    public void checkloan() {
        Date date = new Date();
        List<Loan> loans = loanRepository.findByDateFinBefore(date);
        for (Loan loan : loans) {
            loan.setStatus(true);
            loanRepository.save(loan);
            //send transaction
        }
    }
}

