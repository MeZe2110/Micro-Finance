package tn.esprit.fundsphere.Services.AccountService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;

import java.util.List;
@AllArgsConstructor
@Service

public class AccountServiceImpl implements IAccountService {



    private AccountRepository accountRepository;


    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }



    @Override
    public Account updateAccount(Account account) {
        return  accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long idAccount) {
        accountRepository.deleteById(idAccount);

    }

    @Override
    public List<Account> getAllaccount() {
        return  accountRepository.findAll();
    }

    @Override
    public Account getaccount(Long idAccount) {
        return accountRepository.findById(idAccount).get();
    }
}
