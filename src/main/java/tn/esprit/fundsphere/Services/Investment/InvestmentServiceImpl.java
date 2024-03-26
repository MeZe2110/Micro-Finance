package tn.esprit.fundsphere.Services.Investment;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.InvestmentManagment.InvestStatus;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Invest_stage;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Repositories.IInvestmentRepository;

import java.util.Date;
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
    return investmentRepository.save(investment);
        //return  investment;

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
@Override
    public Investment CreateInvestment(Integer owner_id, Investment investment){
        investment.setAmountInv(0f);
        investment.setStage(Invest_stage.NOT_STARTED);
        investment.setStatus(InvestStatus.UNDER_REVIEW);

        return investmentRepository.save(investment);
    }
    @Override
    public Investment AcceptInvestment(Integer invest_id){
        Investment investment=investmentRepository.findById(invest_id).orElse(null);
        investment.setStatus(InvestStatus.ACCEPTED);
        return investmentRepository.save(investment);
    }
    @Override
    public Investment RefuseInvestment(Integer invest_id){
        Investment investment=investmentRepository.findById(invest_id).orElse(null);
        investment.setStatus(InvestStatus.REFUSED);
        return investmentRepository.save(investment);
    }
    //schedule

    @Scheduled(fixedDelay = 86400000)
    @Override
    public void DisplayInvestment(){
        List<Investment> investments=investmentRepository.findByStageAndStatus(Invest_stage.NOT_STARTED,InvestStatus.ACCEPTED);

        for (Investment inv:investments){
            if (inv.getDate_debut().after(new Date())){
                inv.setStage(Invest_stage.INPROGESS);
                investmentRepository.save(inv);
            }
        }


    }

public Investment Invest(Integer user_id,Float amount_invested, Integer investment_id){
Investment investment=investmentRepository.findById(investment_id).orElse(null);
if(investment.getAmountInv()+amount_invested> investment.getMaxamount()){
    throw new IllegalArgumentException("The amount that you give is higher than we ask , please try with less one");

}
return investment;
    }

}
