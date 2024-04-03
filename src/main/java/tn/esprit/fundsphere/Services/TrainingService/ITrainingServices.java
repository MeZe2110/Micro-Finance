package tn.esprit.fundsphere.Services.TrainingService;

import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;

import java.time.LocalDate;
import java.util.List;

public interface ITrainingServices {

    Training addTraining(Training training);
    Training updateTraining(Training training);
    List<Training> getAllTrainings();
    List<Training> getTrainingsByUserId(int idUser);
    Training getTrainingById(int idTraining);
    void deleteTraining(int idTraining);

    List<TrainingInscription> findTrainingInscriptionsByTrainingAndStartDate(Training training, LocalDate startDate);
}
