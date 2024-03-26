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

    Investment CreateInvestment(Integer owner_id, Investment investment);

    Investment AcceptInvestment(Integer invest_id);

    Investment RefuseInvestment(Integer invest_id);

    void DisplayInvestment();
}
