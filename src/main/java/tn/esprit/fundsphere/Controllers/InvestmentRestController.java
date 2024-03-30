package tn.esprit.fundsphere.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Services.Investment.InvestmentService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/Investment")

public class InvestmentRestController {
    public InvestmentService investmentService;
    @PostMapping("/add")
    Investment AddInvestisement(@RequestBody Investment investment){
        return investmentService.AddInvestment(investment);
    }
    @GetMapping("/show")
    List <Investment> ShowAllInvestment(){
        return investmentService.ShowAllInvestment();
    }
    @DeleteMapping("/delete/{id}")
    void DeleteInvestment(@PathVariable("id") Integer id){
        investmentService.DeleteInvestment(id);
    }
    @GetMapping("/show/{id}")
    Investment ShowInvestment(@PathVariable("id") Integer id){
        return  investmentService.ShowInvestment(id);
    }
    @PutMapping("/update")
    Investment UpdateInvestment(@RequestBody Investment investment){
        return  investmentService.UpdateInvestment(investment);
    }
    @PutMapping("/update/{id}/{amount}")
    Investment UpdateAmount(@PathVariable("id") Integer id,@PathVariable ("amount")float amount){
        return investmentService.UpdateAmount(id,amount);

    }
    @PutMapping("/display")
    void Displayinvest(){
         investmentService.DisplayInvestment();
    }

@PutMapping("/invest/{id_user}/{amount}/{id_invest}")
    Investment invest(@PathVariable("id_user") Long account_id,@PathVariable("amount")Float amount,@PathVariable("id_invest")Integer id_invest){
        return investmentService.Invest(account_id,amount,id_invest);
}

@PutMapping("/accept/{id}")
    Investment AcceptInvestment(@PathVariable("id") Integer id){
        return investmentService.AcceptInvestment(id);
}
    @PutMapping("/refuse/{id}")
    Investment RefuseInvestment(@PathVariable("id") Integer id){
        return investmentService.RefuseInvestment(id);
    }


}