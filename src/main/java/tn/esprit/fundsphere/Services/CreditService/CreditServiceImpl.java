package tn.esprit.fundsphere.Services.CreditService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@AllArgsConstructor
public class CreditServiceImpl implements ICreditService{

    @Autowired



    private CreditRepository creditRepository;
    private TrancheRepository trancheRepository;
    private final EmailService emailService;
    private final SMSService smsService;
    private AccountRepository accountRepository ;

    @Override
    public Credit addCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(Long idCredit) {
         creditRepository.deleteById(idCredit);

    }

    @Override
    public String verifCondition(Credit credit,int idCredit) {
        idCredit=credit.getIdCredit();
        credit=creditRepository.getById((long) idCredit);
        float recoveryTemp;
        recoveryTemp = (float) ((credit.getCreditAmount() * credit.getInterestRate()) / (1 - (Math.pow((1 + credit.getInterestRate()), (-credit.getRecoverySince()))))) / 12;;
        float Salary = credit.getSalary();
        if (recoveryTemp <= Salary * 0.4)
        {
            credit.setAmountRecoveryMonth(recoveryTemp);
            creditRepository.save(credit);
            return ("Recovery amout per month = " +recoveryTemp+" : Condition verified for "+credit.getSurnameClient()+" "+credit.getNameClient());
        }else

            return ("Recovery amout per month = " +recoveryTemp+" : Inverified condition for "+credit.getSurnameClient()+" "+credit.getNameClient());
    }
    @Override
    public Credit updateStateCredit(Credit credit) {
        if (credit.getState() == 1)
        {
            int i;
            for (i=0 ; i<credit.getRecoverySince()*12 ; i++)
            {
                Tranche tranche = new Tranche();
                tranche.setCredit(credit);
                tranche.setAmount(credit.getAmountRecoveryMonth());
                //tranche.setRateRecovery((credit.getAmountRecoveryMonth()*credit.getRecoverySince())- credit.getAmountRecoveryMonth()*(tranche.stream().filter));
                tranche.setStatus(false);
                trancheRepository.save(tranche);
            }


            Mail mail = new Mail();

            mail.setSubject("Credit application decision");
            mail.setTo("mayssendridi21@gmail.com");
            mail.setContent("Mrs,Mr"+credit.getSurnameClient()+" "+credit.getNameClient()+" , we inform you that your credit application has been Accepted. Your interest rate is "+ credit.getInterestRate()+" and your recovery per month is "+credit.getAmountRecoveryMonth()+" and you will return your credit on "+credit.getRecoverySince()+" years");
            emailService.sendSimpleEmail(mail);

            smsService.sendSMS(String.valueOf(50585563),"Welcome to FundSphere \n\r"
                 .concat("Mrs,Mr : "
                         .concat(credit.getSurnameClient()
                                 .concat(" "+credit.getNameClient())
                                    .concat("\"we inform you that your credit application has been Accepted.\n\r "
                                            .concat("\n\r")
                                            .concat("Your interest rate is "+credit.getInterestRate()+"\n\r Your recovery per month is "+credit.getAmountRecoveryMonth()+"\n\r You will return your credit on "+credit.getRecoverySince()+" years")
                                         ))));

        } else if (credit.getState()==2) {
            Mail mail = new Mail();

            mail.setSubject("Credit application decision");
            mail.setTo("mayssendridi21@gmail.com");
            mail.setContent("Mrs,Mr "+credit.getSurnameClient()+" "+credit.getNameClient()+" , we inform you that your credit application has been Declined.");
            emailService.sendSimpleEmail(mail);

            smsService.sendSMS(String.valueOf(50585563),"Welcome to FundSphere \n\r"
                    .concat("Mrs,Mr : "
                            .concat(credit.getSurnameClient()
                                    .concat(" "+credit.getNameClient())
                                    .concat("\"we inform you that your credit application has been Declined.\n\r "))));

        }else
        {
            return credit;

        }
        return credit;
    }

    @Override
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();    }

    @Override
    public Credit getCredit(Long idCredit) {
        return creditRepository.findById(idCredit).get();
    }

    public void assignCreditToAccount( Long idCredit , Long idAccount) {
        Credit credit = creditRepository.findById(idCredit).get();
        Account account = accountRepository.findById(idAccount).get();
// on set le fils dans le parent :
        credit.setAccount(account);
        creditRepository.save(credit);
    }


    public void unassignCreditToAccount(Long idCredit) {
        Credit credit = creditRepository.findById(idCredit).get();
// on set le fils dans le parent :
        credit.setAccount(null);
        creditRepository.save(credit);
    }

}
