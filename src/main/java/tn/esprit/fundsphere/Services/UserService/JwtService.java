package tn.esprit.fundsphere.Services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.UserRepository.TokenRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {


    private final String SECRET_KEY="6dc61ea78fb65f94fb22c249a0fb569d4c0da19e723303468e2d5fddaa15850d";

    private final long jwtExpiration = 86400000;

    private final long jwtRefresh = 604800000;


    private final TokenRepository tokenRepository;

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(
                        getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username =extractUsername(token);

        boolean isValidToken = tokenRepository.findByToken(token)
                .map(t->!t.isLoggedOut())
                .orElse(false);


        return (username.equals(user.getUsername()))&& !isTokenExpired(token) && isValidToken;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims,T>resolver){
       Claims claims= extractAllClaims(token);
       return resolver.apply(claims);
    }

    public String generateToken(User user){

        return buildToken(user,jwtExpiration);
    }

    public String generateRefreshToken(User user){

        return buildToken(user,jwtRefresh);
    }

    private String buildToken(User user , Long expiration){

       String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigninKey())
                .compact();
    return token;
    }

    private SecretKey getSigninKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
