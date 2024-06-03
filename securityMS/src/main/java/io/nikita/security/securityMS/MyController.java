package io.nikita.security.securityMS;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @GetMapping("/error")
//    public String showError(){
//        return new String("No able to process");
//    }
}