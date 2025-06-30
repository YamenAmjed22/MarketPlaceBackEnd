package yamen.marcketplace.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import yamen.marcketplace.Entity.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private String jwtExpirationRaw;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {

        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("email", userDetails.getEmail());
        extraClaims.put("firstname", userDetails.getFirstName());
        extraClaims.put("lastname", userDetails.getLastName());
        extraClaims.put("address", userDetails.getAddress());
        extraClaims.put("phone", userDetails.getPhone());
        extraClaims.put("isValid", userDetails.isValid());
        extraClaims.put("role", userDetails.getRole().name());


        return generateToken(extraClaims, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, User userDetails) {
        return buildToken(extraClaims, userDetails, parseExpirationToMillis(jwtExpirationRaw));
    }


    public long getExpirationTime() {
        return parseExpirationToMillis(jwtExpirationRaw);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private long parseExpirationToMillis(String value) {
        if (value.endsWith("ms")) return Long.parseLong(value.replace("ms", ""));
        if (value.endsWith("s")) return Long.parseLong(value.replace("s", "")) * 1000;
        if (value.endsWith("m")) return Long.parseLong(value.replace("m", "")) * 60 * 1000;
        if (value.endsWith("h")) return Long.parseLong(value.replace("h", "")) * 60 * 60 * 1000;
        if (value.endsWith("d")) return Long.parseLong(value.replace("d", "")) * 24 * 60 * 60 * 1000;
        return Long.parseLong(value); // default fallback to milliseconds
    }


    public String extractRole(String token) {
        return  extractAllClaims(token).get("role",String.class);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
