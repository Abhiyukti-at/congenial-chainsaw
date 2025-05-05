package io.nikita.BankApp.Configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class KeyclockOpaqueRoleConverter implements OpaqueTokenAuthenticationConverter {
    /**
     * @param introspectedToken      the bearer token used to perform token introspection
     * @param authenticatedPrincipal the result of token introspection
     * @return
     */
//    @Override
//    public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
//        String username = authenticatedPrincipal.getAttribute("preferred_username");
//        Map<String,Object> realmAccess = (Map<String, Object>) authenticatedPrincipal.getAttribute("realm_access");
//        if(realmAccess != null && !realmAccess.isEmpty()) {
//            Collection<GrantedAuthority> roles = ((List<String>)realmAccess.get("roles"))
//                    .stream()
//                    .map(role -> "ROLE_" + role)
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//            return new UsernamePasswordAuthenticationToken(authenticatedPrincipal.getName(), null, roles);
//        }
//        return null;
//    }
    @Override
    public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {

        ArrayList<String> roles = authenticatedPrincipal.getAttribute("scope");

        Collection<GrantedAuthority> grantedAuthorities = roles
                .stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(authenticatedPrincipal.getName(), null, grantedAuthorities);

    }
}