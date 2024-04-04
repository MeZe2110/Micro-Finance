package tn.esprit.fundsphere.Controllers.LoanRestController;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Services.AccountService.IAccountService;

import tn.esprit.fundsphere.Services.LoanService.ILoanService;
import tn.esprit.fundsphere.Services.TransactionService.ITransactionService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Loan")
public class LoanRestController {
    public ILoanService loanService;
    public ITransactionService iTransactionService;
    public IAccountService iAccountService;

    @GetMapping("/Show")
    public List<Loan> ShowAllLoans() {
        return loanService.ShowAllLoans();
    }

    @GetMapping("/Show/{id}")
    Loan ShowLoan(@PathVariable("id") Integer id) {
        return loanService.ShowLoan(id);
    }

    @PutMapping("/update")
    Loan UpdateLoan(@RequestBody Loan loan) {
        return loanService.UpdateLoan(loan);
    }

    @DeleteMapping("/delete/{id}")
    void DeleteLoan(@PathVariable("id") Integer id){
        loanService.DeleteLoan(id);
    }


    @PostMapping("/add")
    Loan AddLoan(@RequestBody Loan loan){
        return loanService.AddLoan(loan);
    }
    @PostMapping("/Shows")
    public void ShowAll() throws MessagingException {
         loanService.checkloan();
    }


    @PostMapping("/loan/{amount}/{id}/{periode}")
    public Loan loan(@PathVariable("id") Long id,@PathVariable("amount") Float amount,@PathVariable("periode") Integer periode) throws MessagingException {
      /*  Account account=iAccountService.getAccountById(id);
        Transaction transaction=new Transaction();
        transaction.setSender(account);
        transaction.setReceiver(iAccountService.getAccountById(1L));
        transaction.setAmount(amount);
        transaction.setTypeT(TypeTransaction.LOAN);

        transaction= iTransactionService.addTransaction(transaction); */
        return loanService.loan(id, amount, periode);
    }
    @PutMapping("/check")
    public void CheckLoan() throws MessagingException {
        loanService.checkloan();
    }




}
