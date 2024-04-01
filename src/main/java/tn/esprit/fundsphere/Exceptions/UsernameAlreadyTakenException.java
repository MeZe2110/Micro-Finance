package tn.esprit.fundsphere.Exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{
    public UsernameAlreadyTakenException(String message){
        super(message);
    }
}
