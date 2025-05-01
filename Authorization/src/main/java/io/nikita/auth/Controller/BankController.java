package io.nikita.auth.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    @GetMapping("/welcome")
    public String hello(){
        return "Hello here";

    }

}