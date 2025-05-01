package io.nikita.BankApp.Authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myCards","/myLoan","/myAccount","/myBalance").authenticated()
                .requestMatchers("/contact","/myNotices","/error").permitAll());
        http.formLogin(FormLoginConfigurer -> FormLoginConfigurer.disable());// to disable form login
//        http.httpBasic(basicFormLoginConfigurer -> basicFormLoginConfigurer.disable());//to disable basic login
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    /**
     * InMemoryUserDetailsManager: Using the InMemoryUserDetailsManager to create users.
     * They will be stored in memory and not in a database.
     * @return
     */
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}user").authorities("read")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin").authorities("admin")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }
}