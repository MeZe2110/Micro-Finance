package tn.esprit.fundsphere.Services.CreditService;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class TrancheServiceImpl implements ITrancheService{

    private TrancheRepository trancheRepository ;
    private CreditRepository creditRepository ;
    private AccountRepository accountRepository;
    private EmailService emailService;

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
