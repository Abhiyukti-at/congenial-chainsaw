package io.nikita.BankApp.Configuration;

import io.nikita.BankApp.Exception.CustomAccessDeniedHandler;
import io.nikita.BankApp.Exception.CustomBasicAuthenticationEntryPoint;
import io.nikita.BankApp.Filter.CSRFCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class SecurityConfigProd {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false)) // it is telling the spring context to take care of the session id
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsConfigurer -> corsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.addAllowedOrigin(String.valueOf(Collections.singletonList("http://localhost:4200")));
                        configuration.addAllowedHeader(Collections.singletonList("*").toString());
                        configuration.addAllowedMethod(Collections.singletonList("*").toString());
                        configuration.setAllowCredentials(true);
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                }))
                .csrf(csrfConfig -> csrfConfig
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)
                .requiresChannel(channelConfigurer -> channelConfigurer.anyRequest().requiresSecure()); //only https is allowed that is secured
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/myLoan").hasRole("USER")
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/contact", "/myNotices", "/register", "/error", "/invalidSession", "/expiredSession").permitAll());

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