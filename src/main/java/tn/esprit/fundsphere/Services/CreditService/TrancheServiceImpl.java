package tn.esprit.fundsphere.Services.CreditService;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
//@AllArgsConstructor
@Slf4j
public class TrancheServiceImpl implements ITrancheService {

    @Autowired
    private TrancheRepository trancheRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private AccountRepository accountRepository;

    private EmailService emailService;

   // private final SMSService smsService = new SMSService();


    private int currentTrancheIndex = 0;
    private float alreadyPayed = 0;

   // private float accountBalance = 13000;

    @Override
    public Tranche addTranche(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public void deleteTranche(Long idTranche) {
        trancheRepository.deleteById(idTranche);


    }

    @Override
    public void deleteTranches() {
        trancheRepository.deleteAll();


    }

    @Override
    public Tranche updateTranche(Tranche tranche) {
        return trancheRepository.save(tranche);
    }

    @Override
    public List<Tranche> getAllTranches() {
        return trancheRepository.findAll();
    }

    @Override
    public Tranche getTranche(Long idTranche) {
        return trancheRepository.findById(idTranche).get();
    }

    public void assignTranchesToCredit(Long idTranche, int idCredit) {
        Tranche tranche = trancheRepository.findById(idTranche).get();
        Credit credit = creditRepository.findById(idCredit).orElse(null);
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


    //@Scheduled(cron = "0 0 0 1 * ?") // Exécuter le 1er jour de chaque mois à minuit
    @Scheduled(fixedDelay = 60000)
    public void verifyTrancheAmountInAccount() throws ChangeSetPersister.NotFoundException {
        List<Credit> credits = creditRepository.findByState(1);
        log.info("nbre de credit" + credits.size());

        for (Credit credit : credits) {
            List<Tranche> tranches = trancheRepository.findByCredit(credit);
            log.info("nbre de credit" + tranches.size());

            if (credit.getState() == 1) {
                log.info("state 1");
                if (currentTrancheIndex < tranches.size()) {
                    log.info("tranche ");
                    Tranche tranche = tranches.get(currentTrancheIndex);

                    float amountTranche = tranche.getAmount();
log.info ("tranche Amount" +tranche.getAmount());
//
                    //

                    // float accountBalance = account.getBalance(); // account.getBalance()
                    // Récupérer l'ID de l'account associé à la tranche
                    Credit creditTr = creditRepository.findByTranches_IdTranche(tranche.getIdTranche());
                    log.info ("creditTr " +creditTr.getIdCredit());
                    Account account = accountRepository.findByCredits_IdCredit(creditTr.getIdCredit());
                    log.info ("account " +account.getIdAccount());

                    // Récupérer le solde de l'account
                    double accountBalance = account.getBalance();
                    log.info("accountBalance : " +accountBalance);

                    if (accountBalance < amountTranche) {
                        tranche.setStatus(false);
                        tranche.setRateRecovery(alreadyPayed);

                        System.out.println("Mrs,Mr " + credit.getSurnameClient() + " " + credit.getNameClient() + ",\nWe'd like to inform you that your account has not been debited by the amount of " + credit.getAmountRecoveryMonth() + " for " + tranche.getDateLimit() + "\nThe total amount already payed is " + tranche.getRateRecovery() + ".\nPlease contact us for more information.");

// Envoyer un e-mail en cas d'echec

                        Mail mail = new Mail();
                          mail.setSubject("Alert from your bank");
                          mail.setTo("mayssendridi21@gmail.com");
                          mail.setContent("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that your account has not been debited by the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+"\nThe total amount already payed is "+tranche.getRateRecovery()+".\nPlease contact us for more information.");
                          emailService.sendSimpleEmail(mail);

// Envoyer un SMS en cas d'echec
//                     smsService.sendSMS(String.valueOf(50585563),"Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that your account has not been debited by the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+"\nThe total amount already payed is "+tranche.getRateRecovery()+".\nPlease contact us for more information.");
//

                    } else {
                        tranche.setStatus(true);
                        alreadyPayed += amountTranche;
                        tranche.setRateRecovery(alreadyPayed);


                        accountBalance -= amountTranche;


                        System.out.println("Mrs,Mr " + credit.getSurnameClient() + " " + credit.getNameClient() + ",\nWe'd like to inform you that the amount of " + credit.getAmountRecoveryMonth() + " for " + tranche.getDateLimit() + " has been successfully retrieved from your account.\nThe total amount already payed is " + tranche.getRateRecovery() + ".");

//// Envoyer un e-mail en cas de succès
//
                   Mail mail = new Mail();
                      mail.setSubject("Information from your bank");
                      mail.setTo("mayssendridi21@gmail.com");
                      mail.setContent("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+" has been successfully retrieved from your account.\nThe total amount already payed is "+tranche.getRateRecovery()+".");
                      emailService.sendSimpleEmail(mail);

//// Envoyer un SMS en cas de succès
//                     smsService.sendSMS(String.valueOf(50585563),"Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+",\nWe'd like to inform you that the amount of "+credit.getAmountRecoveryMonth()+" for "+tranche.getDateLimit()+" has been successfully retrieved from your account.\nThe total amount already payed is "+tranche.getRateRecovery()+".");
//
                    }
                    trancheRepository.save(tranche);
                }
                currentTrancheIndex++;


            }
            if (currentTrancheIndex == tranches.size() && alreadyPayed == (credit.getAmountRecoveryMonth() * credit.getRecoverySince() * 12)) {
                credit.setCreditState(true);
                creditRepository.save(credit);
                trancheRepository.deleteAll();
            }
        }
    }
}



