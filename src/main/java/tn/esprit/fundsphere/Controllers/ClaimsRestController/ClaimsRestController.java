package tn.esprit.fundsphere.Controllers.ClaimsRestController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.ClaimsManagment.Claims;
import tn.esprit.fundsphere.Services.ClaimsService.ClaimsServiceImlp;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/claims")
public class ClaimsRestController {


    public ClaimsServiceImlp claimsService ;

    @PostMapping(path = "/add-claims")
    public Claims addClaims (@RequestBody Claims claims)
    {
        return claimsService.addClaims(claims);
    }
    @GetMapping("/show-claims")
    public List<Claims> getAllClaims() {
        List<Claims> listClaims = claimsService.getAllClaims();
        return listClaims;
    }
    @DeleteMapping(path = "/delete-Claims/{id}")
    public void deleteClaims (@PathVariable ("id") Long idClaim)
    {
        claimsService.deleteClaims(idClaim);
    }


    @PutMapping(path = "/update-Claims")
    public Claims updateBloc(@RequestBody Claims c)
    {

        Claims claims = claimsService.updateClaims(c);
        return claims ;
    }
}

