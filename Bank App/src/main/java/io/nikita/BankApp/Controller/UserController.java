package io.nikita.BankApp.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.nikita.BankApp.Constants;
import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.Models.LoginRequestDTO;
import io.nikita.BankApp.Models.LoginResponseDTO;
import io.nikita.BankApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            String hashedPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashedPassword);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getCustomerId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while registering user");
        }
    }

    @RequestMapping("/user")
    public Customer getLoggedInUserDetails(Authentication authentication) {
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        return customer.orElse(null);
    }


    /**
     * This request is for the type of users where we don't have form login and other login option
     * @param loginRequest
     * @return
     */

    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseDTO> doApiLogin(@RequestBody LoginRequestDTO loginRequest) {
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationRes = authenticationManager.authenticate(authentication);
        if (authenticationRes != null && authenticationRes.isAuthenticated()) {

            if (null != environment) {
                String secret = environment.getProperty(Constants.JWT_SECRET, Constants.DEFAULT_SECRET);

                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder().issuer("SecurityBank").subject("JWTToken").claim("username", authenticationRes.getName())
                        .claim("authorities", authenticationRes.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new java.util.Date()).expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(Constants.JWT_HEADER, jwt)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }
}