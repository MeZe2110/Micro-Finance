package tn.esprit.fundsphere.Controllers.UserRestController;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundsphere.Entities.UserManagment.AuthenticationResponse;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Entities.UserManagment.VerificaitonRequest;
import tn.esprit.fundsphere.Services.UserService.AuthenticationService;


import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @GetMapping("/show-users")
    public List<User> getAllUsers() {
        return authenticationService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {

        var response = authenticationService.register(request);
        if (request.isMfaEnabled()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {

        return ResponseEntity.ok(authenticationService.authentication(request));
    }


    @PostMapping("/refresh-token")
    public void refreshToken(

            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody VerificaitonRequest verificaitonRequest) {
        return ResponseEntity.ok(authenticationService.verifyCode(verificaitonRequest));
    }

}

