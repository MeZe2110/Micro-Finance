package tn.esprit.fundsphere.Services.Loan;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Repositories.ILoanRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService{
    private ILoanRepository loanRepository;
    @Override
    public Loan AddLoan(Loan loan){
        return loanRepository.save(loan);
    }
    @Override
    public Loan UpdateLoan(Loan loan){
return loanRepository.save(loan);
    }
    @Override
    public List<Loan>ShowAllLoans(){
return loanRepository.findAll();
    }

    @Override
    public Loan ShowLoan(Integer id){
        return loanRepository.findById(id).orElse(null);
    }
    @Override
    public void DeleteLoan(Integer id){
        loanRepository.deleteById(id);
    }

}
