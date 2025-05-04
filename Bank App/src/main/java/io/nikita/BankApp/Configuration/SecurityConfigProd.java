package io.nikita.BankApp.Configuration;

import io.nikita.BankApp.Exception.CustomAccessDeniedHandler;
import io.nikita.BankApp.Exception.CustomBasicAuthenticationEntryPoint;
import io.nikita.BankApp.Filter.CSRFCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@Profile("prod")
public class SecurityConfigProd {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyclockRoleConverter());
        http
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfigurer -> corsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.addAllowedOrigin(String.valueOf(Collections.singletonList("http://localhost:4200")));
                        configuration.addAllowedHeader(Collections.singletonList("*").toString());
                        configuration.addAllowedMethod(Collections.singletonList("*").toString());
                        configuration.setExposedHeaders(Arrays.asList("Authorization"));
                        configuration.setAllowCredentials(true);
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                }))
                .csrf(csrfConfig -> csrfConfig
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact", "/register", "/apiLogin")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)
              .requiresChannel(channelConfigurer -> channelConfigurer.anyRequest().requiresSecure()); //only https is allowed that is secured
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/myLoan").authenticated()
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/contact", "/myNotices", "/register", "/error").permitAll());
        http.oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
       http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));// to set global exception handling
        http.exceptionHandling(hse -> hse.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

}