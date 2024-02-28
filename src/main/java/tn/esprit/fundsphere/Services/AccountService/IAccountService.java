package tn.esprit.fundsphere.Services.AccountService;

import tn.esprit.fundsphere.Entities.AccountManagment.Account;

import java.util.List;


public interface IAccountService {

    public Account addAccount(Account account);
    public void deleteAccount(Long idAccount);
    public Account updateAccount(Account account);
    public List<Account> getAllaccount();
    public Account getaccount(Long idAccount);

}
