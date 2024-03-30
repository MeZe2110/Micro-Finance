package tn.esprit.fundsphere.Services.Loan;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Entities.TransactionManagment.TypeTransaction;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.ILoanRepository;
import tn.esprit.fundsphere.Services.TransactionService.ITransactionService;
import tn.esprit.fundsphere.Services.TransactionService.TransactionServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private ILoanRepository loanRepository;
    private AccountRepository accountRepository;
    @Autowired
    public ITransactionService iTransactionService;


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
    public Loan loan(Long account_id, Float amount, Integer investmentPeriodInMonths) {
        Account account=accountRepository.findById(account_id).orElse(null);

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
            score_periode = 0.4f;

        } else {
            score_periode = (investmentPeriodInMonths / 48) * 0.5f;
        }
        if (amount >= 50000) {
            score_amount = 0.6f;
        } else {
            score_amount = (amount / 50000) * 0.5f;
        }
        Float score = score_amount + score_periode;
        if (score<0.3){
            score=0.3f;
        }
        loan.setScore(score);
        loan.setInterest((amount * score * 20) / 100);
        // Annuel ( par an compose)
        loan.setStatus(false);


        List<Loan> loans=account.getLoans();
        if (loans==null){
            loans=new ArrayList<>();
        }
       List<Transaction> transactions =loan.getTransactions();
        loans.add(loan);
        account.setLoans(loans);
        loan.setAccount(account);
        Transaction transaction1=new Transaction();
        transaction1.setSender(account);
        transaction1.setReceiver(accountRepository.findById(1L).orElse(null));
        transaction1.setAmount(loan.getAmount());
        transaction1.setTypeT(TypeTransaction.LOAN);
       transaction1.setLoan(loan);
        if (transactions==null){
            transactions=new ArrayList<>();
        }
        transactions.add(transaction1);
        transaction1=iTransactionService.addTransaction(transaction1);
      //  loanRepository.save(loan);
      //  accountRepository.save(account);

        return loan;
    }



    @Scheduled(fixedDelay = 86400000)

    @Override
    public void checkloan() {
        Date date = new Date();
        List<Loan> loans = loanRepository.findByDateFinBefore(date);
        for (Loan loan : loans) {

            loan.setStatus(true);
            loanRepository.save(loan);

        }
    }
}

