package tn.esprit.fundsphere.Services.CreditService;

import tn.esprit.fundsphere.Entities.CrediMangment.Tranche;

import java.util.List;

public interface ITrancheService {
    public Tranche addTranche(Tranche tranche);
    public void deleteTranche(Long idTranche);
    public void deleteTranches();
    public Tranche updateTranche(Tranche tranche);
    public List<Tranche> getAllTranches();
    public Tranche getTranche(Long idTranche);
    public void assignTranchesToCredit(Long idTranche, int idCredit);
    public void dessignerTrancheToCredit(Long idTranche);
}
