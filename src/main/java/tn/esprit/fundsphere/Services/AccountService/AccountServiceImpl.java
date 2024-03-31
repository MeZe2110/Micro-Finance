package tn.esprit.fundsphere.Services.AccountService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.TransactionRepository.TransactionRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;


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

    /*public void assignUserToAccount( Long idAccount , Integer idUser ) {
        Account account = accountRepository.findById(idAccount).get();
        User user = userRepository.findById(idUser).get();
// on set le fils dans le parent :
        account.setUser(user);
        accountRepository.save(account);
    }


    public void unassignUserToAccount(Long idAccount) {
        Account account= accountRepository.findById(idAccount).get();
// on set le fils dans le parent :
        account.setUser(null);
        accountRepository.save(account);
    }*/

///////////////receiver////////////////////////////////////////////////////////

    public void assignAccountTotransactionReceiver( Long idAccount , Long idTransaction ) {
        Transaction transaction = transactionRepository.findById(idTransaction).get();
        Account account = accountRepository.findById(idAccount).get();
// on set le fils dans le parent :
        transaction.setReceiver(account);
        transactionRepository.save(transaction);
    }


    public void unassignAccountToTransactionReceiver(Long idTransaction) {
        Transaction transaction= transactionRepository.findById(idTransaction).get();
// on set le fils dans le parent :
        transaction.setReceiver(null);
        transactionRepository.save(transaction);
    }
/////////////////////// Sender /////////////////////////////


    public void assignAccountTotransactionSender( Long idAccount , Long idTransaction ) {
        Transaction transaction = transactionRepository.findById(idTransaction).get();
        Account account = accountRepository.findById(idAccount).get();
// on set le fils dans le parent :
        transaction.setSender(account);
        transactionRepository.save(transaction);
    }


    public void unassignAccountToTransactionSender(Long idTransaction) {
        Transaction transaction= transactionRepository.findById(idTransaction).get();
// on set le fils dans le parent :
        transaction.setSender(null);
        transactionRepository.save(transaction);
    }

}
