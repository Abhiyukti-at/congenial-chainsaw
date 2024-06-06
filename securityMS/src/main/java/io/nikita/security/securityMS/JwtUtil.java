package io.nikita.security.securityMS;

import io.jsonwebtoken.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.Decoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 60*60*1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }
    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("name",user.getName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }
    public String getToken (HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
        {return bearerToken.substring(7,bearerToken.length()); } // The part after "Bearer "
        return null;
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature.");
//            System.out.println("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
//            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
            System.out.println("Expired JWT token trace: {}");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
//            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
//            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractAllClaims(String token) {return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();}

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
//
//    public String extractUsernameFromToken(String token) {
//        if (isTokenExpired(token)) {
//            return null;
//        }
//        return getClaims(token, Claims::getSubject);
//    }
//
//
//    public boolean isTokenExpired(String token) {
//        Date expiration = getClaims(token, Claims::getExpiration);
//        return expiration.before(new Date());
//    }

//    public <T> T getClaims(String token, Function<Claims, T> resolver) {
//        return resolver.apply(Jwts.parser().notify(getSecretKey()).build().parseSignedClaims(token).getPayload());
//    }
//    private SecretKey getSecretKey() {
//        byte[] keyBytes =
////        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(SignatureAlgorithm.HS256, secret_key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
}