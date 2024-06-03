package io.nikita.security.securityMS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfigWithUsername {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       return http.authorizeHttpRequests((authorize)->authorize.anyRequest()
//                .authenticated()).httpBasic(Customizer.withDefaults()).build();
//    }
}