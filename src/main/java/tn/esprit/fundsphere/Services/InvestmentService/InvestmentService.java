package tn.esprit.fundsphere.Services.InvestmentService;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;

import java.util.List;

public interface InvestmentService {
    Investment AddInvestment(Investment investment);

    Investment UpdateInvestment(Investment investment);

    Investment UpdateAmount(Integer id, float amount);

    void DeleteInvestment(Integer id);

    Investment ShowInvestment(Integer id);

    List<Investment> ShowAllInvestment();

    // For the project owner to create an investment
    Investment CreateInvestment(Long owneraccount_id, Investment investment);

    // In order to accept an Investment ( change statut to accepted)
    Investment AcceptInvestment(Integer invest_id);

    // In order to Refuse an Investment ( change statu to Refused)
    Investment RefuseInvestment(Integer invest_id);

    //schedule
// In Order to start a projet ( change stage to INPROGRESS)
    @Scheduled(fixedDelay = 86400000)
    void DisplayInvestment();

    // For the investor to invest
    Investment Invest(Long account_id, Float amount_invested, Integer investment_id) throws MessagingException;

    void Checkinvest() throws MessagingException;
}
