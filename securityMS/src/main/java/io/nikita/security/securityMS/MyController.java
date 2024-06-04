package io.nikita.security.securityMS;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class MyController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/details")
    public List<User> getUserDetails(){
        List<User> result = userRepository.findAll();
        return result;
    }

}