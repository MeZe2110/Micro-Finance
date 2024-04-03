package tn.esprit.fundsphere.Controllers.AccountRestController;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Services.AccountService.AccountServiceImpl;
import tn.esprit.fundsphere.Services.PDF.PDFGenerator;
import tn.esprit.fundsphere.Services.UserService.AuthenticationService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")

public class AccountRestController {


    public AccountServiceImpl accountService;
    public AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(account);
    }
    @PostMapping(path = "/add-account/{idUser}")
    public Account addAccount(@RequestBody Account account, @PathVariable int idUser) {
        return accountService.addAccount(account, idUser);
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




    //afectation


   @PutMapping("/affecter-user-a-account/{user-id}/{idAccount}")
    public void assignUserToAccount(@PathVariable("user-id") Integer idUser,
                                      @PathVariable("idAccount") Long idAccount) {
       authenticationService.assignUserToAccount(idAccount, idUser);
    }

    @PutMapping("/desaffecter-user-a-account/{idAccount}")
    public void unassignUserToAccount(@PathVariable("idAccount") Long idAccount) {
        authenticationService.unassignUserToAccount(idAccount);
    }

/////////////pdf/////////////////////
    @GetMapping("/client/account/export-to-pdf/{id}")
    public void generatePdfFile(HttpServletResponse response , @PathVariable Long id) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=FinancialStudy" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Account > AccountList = Collections.singletonList(accountService.getaccount(id));
        PDFGenerator generator = new PDFGenerator();
        generator.generate(AccountList, response );
    }


}
