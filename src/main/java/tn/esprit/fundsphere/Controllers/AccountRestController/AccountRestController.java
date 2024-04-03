package tn.esprit.fundsphere.Controllers.AccountRestController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Services.AccountService.AccountServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")

public class AccountRestController {


    public AccountServiceImpl accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(account);
    }
    @PostMapping(path = "/add-account")
    public Account addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping("/show-account")
    public List<Account> getAllaccounts() {
        List<Account> listAccounts = accountService.getAllaccount();
        return listAccounts;
    }



    @PutMapping(path = "/update-account")
    public Account updateAccount(@RequestBody Account Ac)
    {

        Account account = accountService.updateAccount(Ac);
        return account ;
    }




    @DeleteMapping(path = "/delete-account/{id}")
    public void deleteAccount(@PathVariable ("id") Long idAccount) {
        accountService.deleteAccount(idAccount);
    }

}
