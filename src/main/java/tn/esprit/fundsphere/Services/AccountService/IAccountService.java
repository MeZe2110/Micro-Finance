package tn.esprit.fundsphere.Services.AccountService;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;

import java.util.List;


public interface IAccountService {

    public Account addAccount(Account account, int idUser);

    Account getAccountById(Long accountId);

    public void deleteAccount(Long idAccount);
    public Account updateAccount(Account account);
    public List<Account> getAllaccount();
    public Account getaccount(Long idAccount);
    public void assignAccountTotransactionReceiver( Long idAccount , Long idTransaction ) ;
    public void unassignAccountToTransactionReceiver(Long idTransaction);
    public void assignCreditToAccount( int idCredit , Long idAccount);
    public void unassignCreditToAccount(int idCredit);




    public void assignAccountTotransactionSender( Long idAccount , Long idTransaction ) ;
    public void unassignAccountToTransactionSender(Long idTransaction);







    /*public void unassignUserToAccount(Long idAccount);
    public void assignUserToAccount( Long idAccount , Integer idUser );*/

}
