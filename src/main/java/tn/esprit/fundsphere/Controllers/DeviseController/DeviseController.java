package tn.esprit.fundsphere.Controllers.DeviseController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Services.DeviseService.DeviseService;

@AllArgsConstructor
@RestController
@RequestMapping("/devise")
public class DeviseController {

    private DeviseService deviseService;

    @GetMapping("/convertir/{montant}/{deviseSource}/{deviseDestination}")
    public ResponseEntity<Double> convertir(@PathVariable("montant") double montant, @PathVariable("deviseSource") String deviseSource, @PathVariable("deviseDestination") String deviseDestination) {
        double montantConverti = deviseService.convertisseur(montant, deviseSource, deviseDestination);
        return ResponseEntity.ok(montantConverti);
    }
}
