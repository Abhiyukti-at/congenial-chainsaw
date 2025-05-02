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
@Profile("prod")
public class SecurityConfigProd {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession")
                        .maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/expiredSession"))
                .requiresChannel(channelConfigurer -> channelConfigurer.anyRequest().requiresSecure()) //only https is allowed that is secured
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myCards", "/myLoan", "/myAccount", "/myBalance").authenticated()
                .requestMatchers("/contact", "/myNotices", "/register", "/error", "/invalidSession","/expiredSession").permitAll());

        http.formLogin(FormLoginConfigurer -> FormLoginConfigurer.disable());// to disable form login
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));// to set global exception handling
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

}