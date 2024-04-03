package tn.esprit.fundsphere.Repositories.TrainingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.TrainigManagment.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByTraining_IdTraining(int idTraining);

    Feedback findByTraining_IdTrainingAndUser_IdUser(int idTraining, int idUser);

}