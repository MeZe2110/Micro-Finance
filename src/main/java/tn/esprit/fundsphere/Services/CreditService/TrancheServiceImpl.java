package tn.esprit.fundsphere.Services.CreditService;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
=======

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 9ab4a29 (fonctions avancées étape 2)
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Config.EmailService;
import tn.esprit.fundsphere.Config.Mail;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Entities.CrediMangment.Tranche;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.CreditRepository.CreditRepository;
import tn.esprit.fundsphere.Repositories.CreditRepository.TrancheRepository;

import java.util.List;
import java.util.Optional;

@Service
<<<<<<< HEAD
@AllArgsConstructor
=======
//@AllArgsConstructor
>>>>>>> 9ab4a29 (fonctions avancées étape 2)
@Slf4j
public class TrancheServiceImpl implements ITrancheService{

    @Autowired
    private TrancheRepository trancheRepository ;
    private CreditRepository creditRepository ;
    private AccountRepository accountRepository;
    private EmailService emailService;

    @Autowired
    private CreditRepository creditRepository ;

    @Autowired
    private AccountRepository accountRepository;

    private EmailService emailService;

    private final SMSService smsService = new SMSService();


    private int currentTrancheIndex = -1;
    private float alreadyPayed = 0;

    private float accountBalance = 13000;

    @Override
    public Tranche addTranche(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public void deleteTranche(Long idTranche) {
        trancheRepository.deleteById(idTranche);


    }

    @Override
    public Tranche updateTranche(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public List<Tranche> getAllTranches()
    {
        return trancheRepository.findAll();
    }

    @Override
    public Tranche getTranche(Long idTranche)
    {
        return trancheRepository.findById(idTranche).get();
    }

<<<<<<< HEAD
//    public void assignTranchesToCredit(Long idCredit, List<Long> idTranche) {
//        Credit credit = creditRepository.findById(idCredit).orElseThrow(() -> new IllegalArgumentException("Credit not found"));
//        List<Tranche> tranches = trancheRepository.findAllById(idTranche);
//
//        // On set le parent dans les fils :
//        tranches.forEach(tranche -> tranche.setCredit(credit));
//
//        credit.setTranches(tranches);
//        creditRepository.save(credit);
//    }
=======
>>>>>>> 9ab4a29 (fonctions avancées étape 2)
    public void assignTranchesToCredit(Long idTranche, Long idCredit) {
        Tranche tranche = trancheRepository.findById(idTranche).get();
        Credit credit = creditRepository.findById(idCredit).get();
// on set le fils dans le parent :
        tranche.setCredit(credit);
        trancheRepository.save(tranche);
    }

    public void dessignerTrancheToCredit(Long idTranche) {
        Tranche tranche = trancheRepository.findById(idTranche).get();

// on set le fils dans le parent :
        tranche.setCredit(null);
        trancheRepository.save(tranche);
    }

<<<<<<< HEAD
    // @Scheduled(cron = "0 0 0 1 * ?") // Exécuter le 1er jour de chaque mois à minuit
   // @Scheduled(fixedRate = 5000)
    public void verifyTrancheAmountInAccount() {
        //verif si le montant maoujoud fil compte
        List<Credit> credits = creditRepository.findByState(1); // confirmé
        for (Credit credit : credits) {
            List<Tranche> tranches = trancheRepository.findByCredit(credit);
            for (Tranche tranche : tranches) {
                double amount = tranche.getAmount();
                Account account = credit.getAccount();
                log.info("hello from for");
                double accountBalance = account.getBalance();

                if (accountBalance < amount) {
                    log.info("hello");
                    // Envoyer un e-mail d'alerte
//                    String subject = "Alerte: Montant de la tranche manquant dans le compte";
//                    String content = "Cher utilisateur,\n\nLe montant de la tranche mensuelle de " + amount + " est manquant dans votre compte.";
////                    emailService.sendEmail(account.getUser().getEmail(), subject, content);
//                    Mail mail = new Mail();
//
//                    mail.setSubject("Amount Tranche");
//                    mail.setTo("mayssendridi21@gmail.com");
//                    mail.setContent("Mrs,Mr  , you are informed that you do not have the necessary amount in your account .");
//                    emailService.sendSimpleEmail(mail);

                    System.out.println("non confirmé");
                }
            }
        }
    }
}
=======

//@Scheduled(cron = "0 0 0 1 * ?") // Exécuter le 1er jour de chaque mois à minuit
@Scheduled(fixedDelay = 60000)
public void verifyTrancheAmountInAccount() 
    {
        List<Credit> credits = creditRepository.findByState(1);
        for (Credit credit : credits) {
            List<Tranche> tranches = trancheRepository.findByCredit(credit);
            if (currentTrancheIndex < tranches.size()) {
                Tranche tranche = tranches.get(currentTrancheIndex);

                float amountTranche = tranche.getAmount();

                //float accountBalance = 13000; // badel noumrou b account.getBalance()

                if (accountBalance < amountTranche) 
                {
                    tranche.setStatus(false);
                    tranche.setRateRecovery(alreadyPayed);

                System.out.println("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that your account has not been debited by the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+"\nThe total amount already payed is "+tranche.getRateRecovery()+".\nPlease contact us for more information.");

// Envoyer un e-mail en cas d'echec

            //  Mail mail = new Mail();
            //  mail.setSubject("Alert from your bank");
            //  mail.setTo("mayssendridi21@gmail.com");
            //  mail.setContent("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that your account has not been debited by the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+"\nThe total amount already payed is "+tranche.getRateRecovery()+".\nPlease contact us for more information.");
            //  emailService.sendSimpleEmail(mail);

// Envoyer un SMS en cas d'echec
            // smsService.sendSMS(String.valueOf(50585563),"Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that your account has not been debited by the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+"\nThe total amount already payed is "+tranche.getRateRecovery()+".\nPlease contact us for more information.");

                } else 
                {
                    tranche.setStatus(true);
                    alreadyPayed+=amountTranche;
                    tranche.setRateRecovery(alreadyPayed);

                    accountBalance-=amountTranche;
                    

                System.out.println("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+" has been successfully retrieved from your account.\nThe total amount already payed is "+tranche.getRateRecovery()+".");

// Envoyer un e-mail en cas de succès 

            //  Mail mail = new Mail();
            //  mail.setSubject("Information from your bank");
            //  mail.setTo("mayssendridi21@gmail.com");
            //  mail.setContent("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+" has been successfully retrieved from your account.\nThe total amount already payed is "+tranche.getRateRecovery()+".");
            //  emailService.sendSimpleEmail(mail);

// Envoyer un SMS en cas de succès
            // smsService.sendSMS(String.valueOf(50585563),"Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+" has been successfully retrieved from your account.\nThe total amount already payed is "+tranche.getRateRecovery()+".");

                }
                trancheRepository.save(tranche);
            }
        }
        currentTrancheIndex++;
    }
}
>>>>>>> 9ab4a29 (fonctions avancées étape 2)
