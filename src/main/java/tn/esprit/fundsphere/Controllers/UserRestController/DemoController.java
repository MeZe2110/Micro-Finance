package tn.esprit.fundsphere.Controllers.UserRestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/all")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly(){
        return ResponseEntity.ok("hello from adminOnly");
    }

    @GetMapping("/client_only")
    public ResponseEntity<String> clientOnly(){
        return ResponseEntity.ok("hello from clientOnly");
    }

    @GetMapping("/investor_only")
    public ResponseEntity<String> investorOnly(){
        return ResponseEntity.ok("hello from investorOnly");
    }

    @GetMapping("/trainer_only")
    public ResponseEntity<String> trainerOnly(){
        return ResponseEntity.ok("hello from trainerOnly");
    }
}
