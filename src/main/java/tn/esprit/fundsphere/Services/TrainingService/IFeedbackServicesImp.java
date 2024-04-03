package tn.esprit.fundsphere.Services.TrainingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.TrainigManagment.Feedback;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.TrainingRepository.FeedbackRepository;
import tn.esprit.fundsphere.Repositories.TrainingRepository.TrainingInscriptionRepository;
import tn.esprit.fundsphere.Repositories.TrainingRepository.TrainingRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IFeedbackServicesImp implements IFeedbackServices {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingInscriptionRepository trainingInscriptionRepository;
    private final FeedbackRepository feedbackRepository;


    @Override
    public Feedback addFeedback(Feedback feedback, int idUser, int idTraining) {

        User user = userRepository.findById(idUser).orElse(null);
        Training training = trainingRepository.findById(idTraining).orElse(null);

        feedback.setTraining(training);
        feedback.setUser(user);

        feedbackRepository.save(feedback);

        return feedback;
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> findFeedbacksByTrainingId(int idTraining) {
        return feedbackRepository.findByTraining_IdTraining(idTraining);
    }

    @Override
    public Feedback getFeedbacksByUserIdAndTrainingId(int idTraining, int idUser) {
        return feedbackRepository.findByTraining_IdTrainingAndUser_IdUser(idTraining, idUser);
    }

    @Override
    @Transactional
    public void deleteFeedback(int idFeedback) {
        Feedback feedback = feedbackRepository.findById(idFeedback).orElse(null);

        feedback.setUser(null);
        feedback.setTraining(null);

        feedbackRepository.deleteById(idFeedback);
    }
}
