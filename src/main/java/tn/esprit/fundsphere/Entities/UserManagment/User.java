package tn.esprit.fundsphere.Entities.UserManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idUser ;
    String name ;
    String surname ;
    String userName ;
    String password ;
    String Email ;
    String address ;
    int cin ;
    int phone ;
    Date birthdate ;
    Date creationDate ;
    @Enumerated(EnumType.STRING)
    TypeUser role;

    /*@ManyToOne
    Credit credit ;*/

}
