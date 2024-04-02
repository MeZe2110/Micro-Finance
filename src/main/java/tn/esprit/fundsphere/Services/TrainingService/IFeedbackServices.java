package tn.esprit.fundsphere.Services.TrainingService;

import tn.esprit.fundsphere.Entities.TrainigManagment.Feedback;

import java.util.List;

public interface IFeedbackServices {
    Feedback addFeedback(Feedback feedback, int idUser, int idTraining);
    Feedback updateFeedback(Feedback feedback);
    List<Feedback> findFeedbacksByTrainingId(int idTraining);
    Feedback getFeedbacksByUserIdAndTrainingId(int idTraining, int idUser);
    void deleteFeedback(int idFeedback);
}
