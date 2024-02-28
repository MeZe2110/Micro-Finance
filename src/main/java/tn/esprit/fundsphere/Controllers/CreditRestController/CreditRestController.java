package tn.esprit.fundsphere.Controllers.CreditRestController;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entites.CrediMangment.Credit;
import tn.esprit.fundsphere.Services.CreditService.CreditServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/credit")
public class CreditRestController {

    public CreditServiceImpl creditService ;

    @PostMapping(path = "/add-credit")
    public Credit addCredit (@RequestBody Credit credit)
    {
        return creditService.addCredit(credit);
    }
    @GetMapping("/show-credits")
    public List<Credit> getAllCredits() {
        List<Credit> listCredits = creditService.getAllCredits();
        return listCredits;
    }

    @DeleteMapping(path = "/delete-credit/{id}")
    public void deleteCredit (@PathVariable ("id") Long idCredit)
    {
        creditService.deleteCredit(idCredit);
    }


    @PutMapping(path = "/update-credit")
    public Credit updateCredit(@RequestBody Credit cr)
    {

        Credit credit = creditService.updateCredit(cr);
        return credit ;
    }

}
