package io.nikita.APIGateway.gateway;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String getLogin(Authentication authentication){
        String userName = authentication.getName();
        return "Spring Security In-memory Authentication Example - Welcome " + userName;
    }
}