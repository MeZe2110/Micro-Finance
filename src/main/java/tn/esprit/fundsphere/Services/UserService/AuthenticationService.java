package tn.esprit.fundsphere.Services.UserService;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.UserManagment.AuthenticationResponse;
import tn.esprit.fundsphere.Entities.UserManagment.Token;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.UserRepository.TokenRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User request) {
        try {
            User user = new User();
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());

            user = repository.save(user);
            String token = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            saveToken(token, user);

            return new AuthenticationResponse(token, refreshToken);
        } catch (DataIntegrityViolationException e) {
            // Username already exists, provide a custom error message
            throw new IllegalArgumentException("Username is already taken. Please choose a different username.");
        }

    }
    public boolean isUserExists(String username) {
        // Check if a user with the given username exists in the database
        return repository.existsByUsername(username);
    }


    public AuthenticationResponse authentication(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);

        saveToken(token,user);
        return  new AuthenticationResponse(token,refreshToken);
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

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return;
        }

        String refreshToken = authHeader.substring(7);
        String username = jwtService.extractUsername(refreshToken);

        if (username != null ) {

            var userDetails = this.repository.findByUsername(username).orElseThrow();

            if (jwtService.isValid(refreshToken, userDetails)) {

                var accessToken = jwtService.generateToken(userDetails);
                revokeAllTokenByUser(userDetails);
                saveToken(accessToken,userDetails);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}

