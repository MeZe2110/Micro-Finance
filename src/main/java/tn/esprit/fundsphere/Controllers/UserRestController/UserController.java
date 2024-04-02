package tn.esprit.fundsphere.Controllers.UserRestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Entities.UserManagment.ChangePasswordRequest;
import tn.esprit.fundsphere.Services.UserService.UserService;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
        service.changePassword(request,connectedUser);
        return ResponseEntity.accepted().build();

    }
}
