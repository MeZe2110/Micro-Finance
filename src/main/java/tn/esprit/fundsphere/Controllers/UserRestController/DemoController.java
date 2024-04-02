package tn.esprit.fundsphere.Controllers.UserRestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Services.AccountService.IAccountService;

@RestController
public class DemoController {

    private IAccountService accountService;
    @GetMapping("/demo")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly(){
        return ResponseEntity.ok("hello from adminOnly");
    }



}
