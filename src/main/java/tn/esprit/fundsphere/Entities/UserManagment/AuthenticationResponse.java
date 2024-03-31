package tn.esprit.fundsphere.Entities.UserManagment;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String token;
    private String message;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
