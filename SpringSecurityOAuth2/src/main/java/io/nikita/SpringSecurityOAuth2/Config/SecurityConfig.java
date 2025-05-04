package io.nikita.SpringSecurityOAuth2.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/secure").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration github= githubClientRegistration();
////        ClientRegistration facebook= facebookClientRegistration();
//        return new InMemoryClientRegistrationRepository(github);
//    }
//
//    private ClientRegistration githubClientRegistration() {
//       return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .clientId("Ov23livRxqwT0t5jEXrt").clientSecret("8ee1219ebe3c749b5191a013d0b39ff00666b98c").build();
//
//    }

//    private ClientRegistration facebookClientRegistration() {
//        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
//                .clientId("").clientSecret("").build();
//
//    }
}