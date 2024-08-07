package tn.esprit.fundsphere.Services.CreditService;

import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;

import javax.swing.*;
import java.util.List;

@Service
public interface ICreditService {

    public Credit addCredit(Credit credit);
    public void deleteCredit(int idCredit);
    public String verifCondition(int idCredit);
    public Credit updateStateCredit(Credit credit);
    public List<Credit> getAllCredits();
    public Credit getCredit(int idCredit);
//    public void assignCreditToAccount( Long idCredit , Long idAccount);
//    public void unassignCreditToAccount(Long idCredit);
}
