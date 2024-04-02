package tn.esprit.fundsphere.Repositories.TrainingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
    List<Training> findByTrainingInscriptions_Users_IdUser(int idUser);
}