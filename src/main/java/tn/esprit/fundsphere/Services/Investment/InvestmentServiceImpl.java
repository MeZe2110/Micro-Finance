package tn.esprit.fundsphere.Services.Investment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.InvestmentManagment.InvestStatus;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Invest_stage;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.IInvestmentRepository;
import tn.esprit.fundsphere.Services.TransactionService.ITransactionService;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor

public class InvestmentServiceImpl implements InvestmentService{
    private IInvestmentRepository investmentRepository;
    private AccountRepository accountRepository;
    @Autowired
    private ITransactionService iTransactionService;
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

//l owner of the project
@Override
    public Investment CreateInvestment(Long owneraccount_id, Investment investment){
        investment.setAmountInv(0f);
        investment.setStage(Invest_stage.NOT_STARTED);
        investment.setStatus(InvestStatus.UNDER_REVIEW);
        investment.setOwnerId(owneraccount_id);

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
// Start the project
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
// for the investor
    @Override
public Investment Invest(Long account_id, Float amount_invested, Integer investment_id){
Investment investment=investmentRepository.findById(investment_id).orElse(null);
if(investment.getAmountInv()+amount_invested> investment.getMaxamount()){
    throw new IllegalArgumentException("The amount that you give is higher than we ask , please try with less one");

}

    Account account_sender=accountRepository.findById(account_id).orElse(null);

Account account_receiver=accountRepository.findById(investment.getOwnerId()).orElse(null);
Account account_admin=accountRepository.findById(1L).orElse(null);
Float income=amount_invested*0.05f;
Float inv_amount=amount_invested*0.95f;
investment.setAmountInv(investment.getAmountInv()+amount_invested);
    Transaction transaction1=new Transaction();
    transaction1.setAmount(income);
    transaction1.setSender(account_sender);
    transaction1.setReceiver(account_admin);
    transaction1.setInvestment(investment);
    transaction1=iTransactionService.addTransaction(transaction1);
    Transaction transaction2=new Transaction();
    transaction2.setAmount(inv_amount);
    transaction2.setSender(account_sender);
    transaction2.setReceiver(account_receiver);
    transaction2.setInvestment(investment);
    transaction2=iTransactionService.addTransaction(transaction2);

return investmentRepository.save(investment);
    }

}
