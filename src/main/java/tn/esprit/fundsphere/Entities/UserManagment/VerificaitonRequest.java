package tn.esprit.fundsphere.Entities.UserManagment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificaitonRequest {
    private String username;
    private String code;

}
