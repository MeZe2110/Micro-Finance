package tn.esprit.fundsphere.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Services.Loan.LoanService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Loan")
public class LoanRestController {
    public LoanService loanService;

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
    public void ShowAll() {
         loanService.checkloan();
    }


    @PostMapping("/loan/{amount}/{id}/{periode}")
    public Loan loan(@PathVariable("id") Integer id,@PathVariable("amount") Float amount,@PathVariable("periode") Integer periode){
        return loanService.loan(id, amount, periode);
    }




}
