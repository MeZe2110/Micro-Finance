package tn.esprit.fundsphere.Controllers.TrainingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Entities.TrainigManagment.Simulateur;
import tn.esprit.fundsphere.Services.TrainingService.ISimulateurServicesImp;

@RestController
@RequestMapping("/simulateur")
public class SimulateurController {

    private final ISimulateurServicesImp simulateurService;

    @Autowired
    public SimulateurController(ISimulateurServicesImp simulateurService) {
        this.simulateurService = simulateurService;
    }

    @PostMapping("/remboursement-mensuel")
    public double calculateRemboursementMensuel(@RequestBody Simulateur simulateur) {
        return simulateurService.calculateRemboursementMensuel(simulateur);
    }

    @PostMapping("/taux-endettement-mensuel")
    public double calculateTauxEndettementMensuel(@RequestBody Simulateur simulateur) {
        return simulateurService.calculateTauxEndettementMensuel(simulateur);
    }
}
