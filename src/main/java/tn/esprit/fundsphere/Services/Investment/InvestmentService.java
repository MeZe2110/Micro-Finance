package tn.esprit.fundsphere.Services.Investment;

import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;

import java.util.List;

public interface InvestmentService {

    Investment AddInvestment(Investment investment);

    Investment UpdateInvestment(Investment investment);

    Investment UpdateAmount(Integer id, float amount);

    void DeleteInvestment(Integer id);

    Investment ShowInvestment(Integer id);

    List<Investment> ShowAllInvestment();

    Investment CreateInvestment(Long owner_id, Investment investment);

    Investment AcceptInvestment(Integer invest_id);

    Investment RefuseInvestment(Integer invest_id);

    void DisplayInvestment();

    Investment Invest(Long account_id, Float amount_invested, Integer investment_id);
}
