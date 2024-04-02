package tn.esprit.fundsphere.Controllers.TrainingController;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;
import tn.esprit.fundsphere.Services.TrainingService.ITrainingInscriptionServices;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/trainingInscription")
@RequiredArgsConstructor
public class TrainingInscriptionController {

    private final ITrainingInscriptionServices trainingInscriptionServices;

    @PostMapping("/add/{idUser}/{idTraining}")
    public TrainingInscription addTrainingInscription(
            @RequestBody TrainingInscription trainingInscription,
            @PathVariable int idUser,
            @PathVariable int idTraining) throws MessagingException {

        return trainingInscriptionServices.addTrainingInscription(trainingInscription, idUser, idTraining);
    }

    @PutMapping("/cancel/{idTrainingInscription}")
    public TrainingInscription annulerTrainingInscription(@PathVariable int idTrainingInscription) throws MessagingException {
        return trainingInscriptionServices.annulerTrainingInscription(idTrainingInscription);
    }

    @GetMapping("/byTraining/{idTraining}")
    public List<TrainingInscription> getTrainingInscriptionsByTrainingId(@PathVariable int idTraining) {
        return trainingInscriptionServices.getTrainingInscriptionsByTrainingId(idTraining);
    }

    @GetMapping("/byUser/{idUser}")
    public List<TrainingInscription> getTrainingInscriptionsByUserId(@PathVariable int idUser) {
        return trainingInscriptionServices.getTrainingInscriptionsByUserId(idUser);
    }

    @GetMapping("/{idTrainingInscription}")
    public TrainingInscription getById(@PathVariable int idTrainingInscription) {
        return trainingInscriptionServices.getById(idTrainingInscription);
    }
}
