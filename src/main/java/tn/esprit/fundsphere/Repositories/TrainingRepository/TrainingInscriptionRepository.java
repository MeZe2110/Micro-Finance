package tn.esprit.fundsphere.Repositories.TrainingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;

import java.time.LocalDate;
import java.util.List;

public interface TrainingInscriptionRepository extends JpaRepository<TrainingInscription, Integer> {
    List<TrainingInscription> findByTraining_IdTraining(int idTraining);

    List<TrainingInscription> findByUsers_IdUser(int idUser);

    @Query("SELECT ti FROM TrainingInscription ti JOIN FETCH ti.users WHERE ti.training = :training AND ti.training.startDate = :startDate")
    List<TrainingInscription> findTrainingInscriptionsByTrainingAndStartDate(@Param("training") Training training, @Param("startDate") LocalDate startDate);
}