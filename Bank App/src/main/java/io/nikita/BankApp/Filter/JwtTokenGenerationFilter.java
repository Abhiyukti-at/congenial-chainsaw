package io.nikita.BankApp.Filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.nikita.BankApp.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtTokenGenerationFilter extends OncePerRequestFilter {
    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            Environment environment = getEnvironment();
            if (null != environment) {
                String secret = environment.getProperty(Constants.JWT_SECRET, Constants.DEFAULT_SECRET);

                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                String jwt = Jwts.builder().issuer("SecurityBank").subject("JWTToken").claim("username", authentication.getName())
                        .claim("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date()).expiration(new Date((new Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
                response.setHeader(Constants.JWT_HEADER, jwt);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * only execute this filter when the condition satisfy. It should be executed only for the request calls after login
     *
     * @param request current HTTP request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user");
    }
}