package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.RedisLock.LockManager;
import io.nikita.BankApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private LockManager lockManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        String lockKey = "register:" + customer.getEmail();
        String lockId = lockManager.acquireLock(lockKey);
        try {
            String hashedPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashedPassword);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            if(lockId == null) {
                System.out.println("Registration is currently locked for email: " + customer.getEmail());
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Please try again later, registration is currently locked");
            }
            System.out.println("Lock acquired for registration: " + lockId);
            Customer savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getCustomerId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while registering user");
        }finally {
            System.out.println("Releasing lock for registration: " + lockId);
            lockManager.releaseLock(lockKey, lockId);
        }
    }

    @RequestMapping("/user")
    public Customer getLoggedInUserDetails(Authentication authentication){
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        return customer.orElse(null);
    }
}