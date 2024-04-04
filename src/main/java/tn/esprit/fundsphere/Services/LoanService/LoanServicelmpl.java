package tn.esprit.fundsphere.Services.LoanService;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Config.EmailService;
import tn.esprit.fundsphere.Config.Mail;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Entities.TransactionManagment.TypeTransaction;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.LoanRepository.ILoanRepository;
import tn.esprit.fundsphere.Services.TransactionService.ITransactionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanServicelmpl implements ILoanService {
    private ILoanRepository loanRepository;
    private AccountRepository accountRepository;
    @Autowired
    public ITransactionService iTransactionService;
    private EmailService emailService;


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
    public Loan loan(Long account_id, Float amount, Integer investmentPeriodInMonths) throws MessagingException {

        Account account=accountRepository.findById(account_id).orElse(null);
        if(account.getBalance()<amount) {
            Mail mail = new Mail();
            mail.setTo(account.getUser().getEmail());
            mail.setSubject("Loan Failed");
            mail.setContent("Dear " + account.getUser().getName() + ",\n\nYour loan of " + amount + " can't be done since you have  insufficient balance.");
            emailService.sendHtmlEmail(mail);
            throw new IllegalArgumentException("Insufficient balance");
        }

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

            if (investmentPeriodInMonths >= 48) {
                score_periode = 0.4f;
            } else {
                score_periode = ((float) investmentPeriodInMonths / 48) * 0.4f;
            }

            if (amount >= 50000) {
                score_amount = 0.6f;
            } else {
                score_amount = ((float) amount / 50000) * 0.6f;
            }

            Float score = score_amount + score_periode;

            if (score < 0.3) {
                score = 0.3f;
            }
            loan.setScore(score);
            loan.setInterest((amount * score * 20) / 100);
            // Annuel ( par an compose)
            loan.setStatus(false);


            List<Transaction> transactions = loan.getTransactions();

            loan.setAccount_id(account_id);
            Transaction transaction1 = new Transaction();
            transaction1.setSender(account);
            transaction1.setReceiver(accountRepository.findById(1L).orElse(null));
            transaction1.setAmount(loan.getAmount());
            transaction1.setTypeT(TypeTransaction.LOAN);
            transaction1.setLoan(loan);
            if (transactions == null) {
                transactions = new ArrayList<>();
            }
            transactions.add(transaction1);
            loanRepository.save(loan);
            transaction1 = iTransactionService.addTransaction(transaction1);
        Mail successMail = new Mail();
        successMail.setTo(account.getUser().getEmail());
        successMail.setSubject(" Successful Loan ");
        successMail.setContent("Dear  " + account.getUser().getName() + ",\n\n You are now one of our stakeholders since your loan of " + amount + "   has been successfully received." +
                "\n\n you will receive your loan plus the interest "+loan.getInterest()+" in  "+loan.getInvestmentPeriodInMonths() +"months.");
        emailService.sendHtmlEmail(successMail);
        return loan;
    }



   // @Scheduled(fixedDelay = 86400000)

    @Override
    public void checkloan() throws MessagingException {
        Date date = new Date();
        List<Loan> loans = loanRepository.findByDateFinBefore(date);
        for (Loan loan : loans) {
            if (!loan.isStatus()){
                loan.setStatus(true);
                loanRepository.save(loan);
                Transaction transaction1=new Transaction();
                transaction1.setAmount(loan.getAmount()+loan.getInterest());
                transaction1.setSender(accountRepository.findById(1L).orElse(null));
                transaction1.setReceiver(accountRepository.findById(loan.getAccount_id()).orElse(null));
                transaction1.setDate(LocalDate.now());
                transaction1.setTypeT(TypeTransaction.RETURN_LOAN);
                iTransactionService.addTransaction(transaction1);
                Account account=accountRepository.findById(loan.getAccount_id()).orElse(null);
                Mail mail = new Mail();
                mail.setTo(account.getUser().getEmail());
                mail.setSubject("Your Loan Return is HERE");
                mail.setContent("Dear " + account.getUser().getName() + ",\n\nYour loan of " + loan.getAmount() + " is back to you with as promised with "+loan.getInterest()+" interest.");
                emailService.sendHtmlEmail(mail);

            }}
    }
}
