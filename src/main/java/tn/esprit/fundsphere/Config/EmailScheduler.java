package tn.esprit.fundsphere.Config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.fundsphere.Entities.TrainigManagment.Training;
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainingInscription;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Services.TrainingService.ITrainingServices;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailScheduler {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private ITrainingServices trainingService;

    // Méthode de service pour envoyer un e-mail à un utilisateur
    public void sendHtmlEmail(String userEmail, String trainingTitle) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@fundsphere.com");
        helper.setTo(userEmail);
        helper.setSubject("Rappel : Formation " + trainingTitle);

        // Contenu HTML de l'e-mail
        String htmlContent = "<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\"><style>html, body {height: 100%;}body {display: flex;align-items: center;justify-content: center;min-height: 100vh;margin: 0;}</style></head><body><div class=\"card text-center shadow-lg p-3 mb-5 bg-body rounded\" style=\"width: 30rem;\"><img class=\"card-img-top mx-auto d-block\" src=\"https://res.cloudinary.com/dvofvctg3/image/upload/v1709975801/aviowneosir0zhnyv92j.png\" style=\"width: 8rem; height: 8rem;\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">Rappel : Formation " + trainingTitle + "</h5><br><p class=\"card-text\">Bonjour,<br><br>Ceci est un rappel que la formation " + trainingTitle + " débutera demain. Assurez-vous d'être prêt !</p></div><div class=\"card-footer\"><p class=\"card-text\">Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email.</p></div></div><script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script></body></html>";

        // Définir le contenu HTML
        helper.setText(htmlContent, true);

        // Envoyer l'e-mail
        javaMailSender.send(message);
    }


    // Méthode de service pour récupérer les utilisateurs inscrits à une formation un jour avant la date de début
    public List<User> getUsersToRemind(Training training) {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        List<TrainingInscription> trainingInscriptions = trainingService.findTrainingInscriptionsByTrainingAndStartDate(training, tomorrow);
        List<User> usersToRemind = new ArrayList<>();
        for (TrainingInscription trainingInscription : trainingInscriptions) {
            // Charger explicitement la collection d'utilisateurs
            Hibernate.initialize(trainingInscription.getUsers());
            usersToRemind.addAll(trainingInscription.getUsers());
        }
        return usersToRemind;
    }

    // Méthode de planification pour envoyer des e-mails de rappel chaque jour à minuit
    @Scheduled(cron = "0 * * * * *")
    //@Scheduled(cron = "0 0 0 * * *")
    public void sendReminderEmails() {
        List<Training> trainings = trainingService.getAllTrainings();
        for (Training training : trainings) {
            List<User> usersToRemind = getUsersToRemind(training);
            for (User user : usersToRemind) {
                try {
                    sendHtmlEmail(user.getEmail(), training.getTitle());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
