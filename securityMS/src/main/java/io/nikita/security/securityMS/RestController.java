package io.nikita.security.securityMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/users")
public class RestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/details")
    public ResponseEntity<User> getUSerDetails(){
         ResponseEntity<User> result = (ResponseEntity<User>) userRepository.findAll();
        return result;
    }

}