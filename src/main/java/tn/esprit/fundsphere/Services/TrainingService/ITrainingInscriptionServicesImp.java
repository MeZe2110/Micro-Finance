package tn.esprit.fundsphere.Services.TrainingService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Config.EmailService;
import tn.esprit.fundsphere.Config.Mail;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.TrainingRepository.FeedbackRepository;
import tn.esprit.fundsphere.Repositories.TrainingRepository.TrainingInscriptionRepository;
import tn.esprit.fundsphere.Repositories.TrainingRepository.TrainingRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ITrainingInscriptionServicesImp implements ITrainingInscriptionServices{

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingInscriptionRepository trainingInscriptionRepository;
    private final FeedbackRepository feedbackRepository;
    private final EmailService emailService;


    @Override
    public TrainingInscription addTrainingInscription(TrainingInscription trainingInscription, int idUser, int idTraining) throws MessagingException {
        User user = userRepository.findById(idUser).orElse(null);
        Training training = trainingRepository.findById(idTraining).orElse(null);

        // Vérifier si la capacité actuelle est inférieure à la capacité maximale
        if (training.getActuelleCapacity() < training.getMaxCapacity()) {
            // Récupérer les utilisateurs existants ou créer un nouvel ensemble s'il est null
            Set<User> usersSet = trainingInscription.getUsers();
            if (usersSet == null) {
                usersSet = new HashSet<>();
            }

            usersSet.add(user);

            trainingInscription.setStatut("Confirmer");
            trainingInscription.setDateInscription(LocalDate.now());
            trainingInscription.setTraining(training);
            trainingInscription.setUsers(usersSet);

            // Augmenter la capacité actuelle
            training.setActuelleCapacity(training.getActuelleCapacity() + 1);
            trainingRepository.save(training);

            Mail mail = new Mail();

            mail.setSubject("Inscription avec succès");
            mail.setTo(user.getEmail());
            String htmlContent = "<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\"><style>html, body {height: 100%;}body {display: flex;align-items: center;justify-content: center;min-height: 100vh;margin: 0;}</style></head><body><div class=\"card text-center shadow-lg p-3 mb-5 bg-body rounded\" style=\"width: 30rem;\"><img class=\"card-img-top mx-auto d-block\" src=\"https://res.cloudinary.com/dvofvctg3/image/upload/v1709975801/aviowneosir0zhnyv92j.png\" style=\"width: 8rem; height: 8rem;\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">Inscription avec succès</h5><br><p class=\"card-text\">Vous êtes inscrit(e) avec succès à la formation : " + training.getTitle() + "</p></div><div class=\"card-footer\"><p class=\"card-text\">Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email.</p></div></div><script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script></body></html>";
            mail.setContent(htmlContent);
            emailService.sendHtmlEmail(mail);

            return trainingInscriptionRepository.save(trainingInscription);
        } else {
            // Capacité maximale atteinte
            return null;
        }
    }


    @Override
    public List<TrainingInscription> getTrainingInscriptionsByTrainingId(int idTraining) {
        return trainingInscriptionRepository.findByTraining_IdTraining(idTraining);
    }

    @Override
    public List<TrainingInscription> getTrainingInscriptionsByUserId(int idUser) {
        return trainingInscriptionRepository.findByUsers_IdUser(idUser);
    }

    @Override
    public TrainingInscription getById(int idTrainingInscription) {
        return trainingInscriptionRepository.findById(idTrainingInscription).orElse(null);
    }

    @Override
    public TrainingInscription annulerTrainingInscription(int idTrainingInscription) throws MessagingException {
        TrainingInscription trainingInscription = trainingInscriptionRepository.findById(idTrainingInscription).orElse(null);

        Training training = trainingInscription.getTraining();
        if (training != null) {
            // Décrémenter la capacité actuelle de la formation
            training.setActuelleCapacity(training.getActuelleCapacity() - 1);
            trainingRepository.save(training);
        }

        trainingInscription.setStatut("Annuler");

        Mail mail = new Mail();
        mail.setSubject("Annulation d'inscription");

        // Récupérer tous les utilisateurs inscrits à cette formation et leur envoyer un e-mail d'annulation
        for (User user : trainingInscription.getUsers()) {
            mail.setTo(user.getEmail());
            String htmlContent = "<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\"><style>html, body {height: 100%;}body {display: flex;align-items: center;justify-content: center;min-height: 100vh;margin: 0;}</style></head><body><div class=\"card text-center shadow-lg p-3 mb-5 bg-body rounded\" style=\"width: 30rem;\"><img class=\"card-img-top mx-auto d-block\" src=\"https://res.cloudinary.com/dvofvctg3/image/upload/v1709975801/aviowneosir0zhnyv92j.png\" style=\"width: 8rem; height: 8rem;\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">Annulation d'inscription</h5><br><p class=\"card-text\">Votre inscription à la formation " + trainingInscription.getTraining().getTitle() + " a été annulée.</p></div><div class=\"card-footer\"><p class=\"card-text\">Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email.</p></div></div><script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script></body></html>";
            mail.setContent(htmlContent);
            emailService.sendHtmlEmail(mail);
        }

        return trainingInscriptionRepository.save(trainingInscription);
    }

}
