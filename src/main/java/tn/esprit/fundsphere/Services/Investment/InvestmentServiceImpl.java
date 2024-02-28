package tn.esprit.fundsphere.Services.Investment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Repositories.IInvestmentRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class InvestmentServiceImpl implements InvestmentService{
    private IInvestmentRepository investmentRepository;
    @Override
    public Investment AddInvestment(Investment investment){

        return investmentRepository.save(investment);
}
    @Override
public Investment UpdateInvestment(Investment investment){
        return investmentRepository.save(investment);
}
    @Override
public Investment UpdateAmount(Integer id, float amount){
        Investment investment = investmentRepository.findById(id).orElse(null);
   investment.setAmountInv(amount);
    //return investmentRepository.save(investment);
        return  investment;

}
@Override
public void DeleteInvestment(Integer id){
        investmentRepository.deleteById(id);
}
@Override
public Investment ShowInvestment(Integer id){
return investmentRepository.findById(id).orElse(null);
}
@Override
public List<Investment> ShowAllInvestment(){
        return investmentRepository.findAll();
}
}
