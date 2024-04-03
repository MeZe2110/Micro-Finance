package tn.esprit.fundsphere.Services.TrainingService;

import jakarta.mail.MessagingException;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;

import java.util.List;

public interface ITrainingInscriptionServices {
    TrainingInscription addTrainingInscription(TrainingInscription trainingInscription, int idUser, int idTraining) throws MessagingException;
    List<TrainingInscription> getTrainingInscriptionsByTrainingId(int idTraining);
    List<TrainingInscription> getTrainingInscriptionsByUserId(int idUser);
    TrainingInscription getById(int idTrainingInscription);
    TrainingInscription annulerTrainingInscription(int idTrainingInscription) throws MessagingException;
}
