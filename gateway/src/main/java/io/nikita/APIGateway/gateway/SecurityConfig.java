package io.nikita.APIGateway.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((authorize) -> authorize
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());
//                    .formLogin(Customizer.withDefaults()
//                    );

            return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        User.UserBuilder user = User.withDefaultPasswordEncoder();
//        manager.createUser(user.username("user").password("password").roles("USER").build());
//        UserDetails userDetails = User.withUsername("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        InMemoryUserDetailsManager manager =new InMemoryUserDetailsManager(userDetails);
//        boolean res =manager.userExists("user");
////        manager.setAuthenticationManager((AuthenticationManager) userDetails);
//        return manager;
////        return manager;
        UserDetails user = User.builder()
                .username("nikita")
                .password(passwordEncoder().encode("nikita"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}