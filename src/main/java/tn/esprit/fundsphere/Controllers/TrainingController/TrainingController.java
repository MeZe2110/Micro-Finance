package tn.esprit.fundsphere.Controllers.TrainingController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Services.TrainingService.ITrainingServices;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final ITrainingServices trainingServices;

    @PostMapping("/add")
    public Training addTraining(@RequestBody @Valid Training training) {
        return trainingServices.addTraining(training);
    }

    @PutMapping("/update")
    public Training updateTraining(@RequestBody @Valid Training training) {
        return trainingServices.updateTraining(training);
    }

    @GetMapping("/all")
    public List<Training> getAllTrainings() {
        return trainingServices.getAllTrainings();
    }

    @GetMapping("/byUser/{idUser}")
    public List<Training> getTrainingsByUserId(@PathVariable int idUser) {
        return trainingServices.getTrainingsByUserId(idUser);
    }

    @GetMapping("/{idTraining}")
    public Training getTrainingById(@PathVariable int idTraining) {
        return trainingServices.getTrainingById(idTraining);
    }

    @DeleteMapping("/{idTraining}")
    public void deleteTraining(@PathVariable int idTraining) {
        trainingServices.deleteTraining(idTraining);
    }
}
