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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .securityContext(contextConfig-> contextConfig.requireExplicitSave(false)) // it is telling the spring context to take care of the session id
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsConfigurer -> corsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.addAllowedOrigin("http://localhost:4200");
                        configuration.addAllowedHeader("*");
                        configuration.addAllowedMethod("*");
                        configuration.setAllowCredentials(true);
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                }))
                .csrf(csrfConfig->csrfConfig
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact","/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)// max 3 session allowed,and more than that you'll be redirect
                .requiresChannel(channelConfigurer -> channelConfigurer.anyRequest().requiresInsecure()) //to have non-secure
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myCards", "/myLoan", "/myAccount", "/myBalance","/user").authenticated()
                        .requestMatchers("/contact", "/myNotices", "/register", "/error", "/invalidSession", "/expiredSession").permitAll());

        http.formLogin(FormLoginConfigurer -> FormLoginConfigurer.disable());// to disable form login
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(hse -> hse.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    /**
     * This one putting all the other stuff that can be used with comments, we can have a clean method to practise then.
     *
     * @param
     * @return
     * @throws Exception
     */

//    SecurityFilterChain defaultSecurityFilterChain1(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll()); // To deny all requests
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll()); // to allow all request
//        http
//                .sessionManagement(smc -> smc
//                        .sessionFixation(sfc -> sfc.none())//for disabling session fixation
//                        .sessionFixation(sfc -> sfc.changeSessionId())// use change session id strategy
//                        .sessionFixation(sfc -> sfc.newSession())//to create a new session every time
//                        .sessionFixation(sfc -> sfc.migrateSession())// to use migrate session strategy
//                        .invalidSessionUrl("/invalidSession")
//                        .maximumSessions(3).maxSessionsPreventsLogin(true).expiredUrl("/expiredSession"))// max 3 session allowed,and more than that you'll be redirect
//                .requiresChannel(channelConfigurer -> channelConfigurer.anyRequest().requiresInsecure()) //to have non-secure
//                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/myCards").authenticated()
//                        .requestMatchers("/expiredSession").permitAll());
//
//        http.formLogin(FormLoginConfigurer -> FormLoginConfigurer.disable());// to disable form login
//        http.httpBasic(basicFormLoginConfigurer -> basicFormLoginConfigurer.disable());//to disable basic login
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());//for default configuration
//        //Custom exception Handling
//        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
//        //basic exception handling does not work together with form login, it supports postman
//        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
//                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));// to set global exception handling
//        http.exceptionHandling(hse -> hse.accessDeniedHandler(new CustomAccessDeniedHandler()));
//        return http.build();
//    }
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