package io.nikita.BankApp.Configuration;

import io.nikita.BankApp.Exception.CustomAccessDeniedHandler;
import io.nikita.BankApp.Exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll()); // To deny all requests
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll()); // To allow all request
        http
                .sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession")
                        .maximumSessions(3).maxSessionsPreventsLogin(true).expiredUrl("/expiredSession"))// max 3 session allowed,and more than that you'll be redirect
                .requiresChannel(channelConfigurer -> channelConfigurer.anyRequest().requiresInsecure()) //to have non-secure
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myCards", "/myLoan", "/myAccount", "/myBalance").authenticated()
                        .requestMatchers("/contact", "/myNotices", "/register", "/error", "/invalidSession","/expiredSession").permitAll());

        http.formLogin(FormLoginConfigurer -> FormLoginConfigurer.disable());// to disable form login
//        http.httpBasic(basicFormLoginConfigurer -> basicFormLoginConfigurer.disable());//to disable basic login
        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());//for default configuration
        //Custom exception Handling
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        //basic exception handling does not work together with form login, it supports postman
//        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));// to set global exception handling

        http.exceptionHandling(hse -> hse.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

//    /**
//     * JdbcUserDetailsManager: Using the JdbcUserDetailsManager to create users.
//     * They will be stored in a database.
//     *
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    /**
     * InMemoryUserDetailsManager: Using the InMemoryUserDetailsManager to create users.
     * They will be stored in memory and not in a database.
     * @return
     */
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password("{bcrypt}$2a$12$UisLpw2uJVXq4yEiRNsmmeD/e1RcOSdWKB9RfllB2c.oZifhtwh4S").authorities("read") //User@9422!
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}Admin@7877!").authorities("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }
}