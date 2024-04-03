package tn.esprit.fundsphere.Services.TrainingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;
import tn.esprit.fundsphere.Repositories.TrainingRepository.FeedbackRepository;
import tn.esprit.fundsphere.Repositories.TrainingRepository.TrainingInscriptionRepository;
import tn.esprit.fundsphere.Repositories.TrainingRepository.TrainingRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ITrainingServicesImp implements ITrainingServices {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingInscriptionRepository trainingInscriptionRepository;
    private final FeedbackRepository feedbackRepository;


    @Override
    public Training addTraining(Training training) {
        training.setActuelleCapacity(0);
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(int idUser) {
        return trainingRepository.findByTrainingInscriptions_Users_IdUser(idUser);
    }

    @Override
    public Training getTrainingById(int idTraining) {
        return trainingRepository.findById(idTraining).orElse(null);
    }

    @Override
    public void deleteTraining(int idTraining) {
        trainingRepository.deleteById(idTraining);
    }

    @Override
    public List<TrainingInscription> findTrainingInscriptionsByTrainingAndStartDate(Training training, LocalDate startDate) {
        return trainingInscriptionRepository.findTrainingInscriptionsByTrainingAndStartDate(training, startDate);
    }
}
