package tn.esprit.fundsphere.Services.CreditService;

import tn.esprit.fundsphere.Entities.CrediMangment.Credit;

import java.util.List;

public interface ICreditService {

    public Credit addCredit(Credit credit);
    public void deleteCredit(Long idCredit);
    public Credit updateCredit(Credit credit);
    public List<Credit> getAllCredits();
    public Credit getCredit(Long idCredit);

}
