package tn.esprit.fundsphere.Services.AccountService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.ClaimsRepository.ClaimsRepository;
import tn.esprit.fundsphere.Repositories.CreditRepository.CreditRepository;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CreditRepository creditRepository ;


    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }
    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
    }
    @Override
    public void deleteAccount(Long idAccount) {
        accountRepository.deleteById(idAccount);
    }
    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllaccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getaccount(Long idAccount) {
        return accountRepository.findById(idAccount).get();
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
