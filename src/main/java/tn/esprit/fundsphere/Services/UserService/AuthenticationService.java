package tn.esprit.fundsphere.Services.UserService;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.UserManagment.AuthenticationResponse;
import tn.esprit.fundsphere.Entities.UserManagment.Token;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.TokenRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.util.List;


@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AccountRepository accountRepository, TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        user = repository.save(user);
        String token = jwtService.generateToken(user);

        saveToken(token, user);

        return new AuthenticationResponse(token);

    }


    public AuthenticationResponse authentication(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        revokeAllTokenByUser(user);

        saveToken(token,user);
        return  new AuthenticationResponse(token);
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokenListByUser =tokenRepository.findAllByTokenByUser(user.getIdUser());

        if(!validTokenListByUser.isEmpty()){

            validTokenListByUser.forEach(t->{
                t.setLoggedOut(true);
            });

        }

        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveToken(String token, User user) {
        Token token1 = new Token();
        token1.setToken(token);
        token1.setLoggedOut(false);
        token1.setUser(user);
        tokenRepository.save(token1);
    }

    public void assignUserToAccount( Long idAccount , Integer idUser ) {
        Account account = accountRepository.findById(idAccount).get();
        User user = repository.findById(idUser).get();
// on set le fils dans le parent :
        account.setUser(user);
        accountRepository.save(account);
    }


    public void unassignUserToAccount(Long idAccount) {
        Account account= accountRepository.findById(idAccount).get();
// on set le fils dans le parent :
        account.setUser(null);
        accountRepository.save(account);
    }
}

