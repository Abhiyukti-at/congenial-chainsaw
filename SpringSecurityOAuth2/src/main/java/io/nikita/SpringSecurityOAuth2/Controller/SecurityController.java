package io.nikita.SpringSecurityOAuth2.Controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/secure")
    public String securePage(Authentication authentication) {
        if(authentication instanceof UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
            System.out.println("User with simple: " + usernamePasswordAuthenticationToken);
        }else if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
            System.out.println("User with OAuth: " + oAuth2AuthenticationToken.getPrincipal().getAttributes());

        }
        return "secure.html";
    }
}