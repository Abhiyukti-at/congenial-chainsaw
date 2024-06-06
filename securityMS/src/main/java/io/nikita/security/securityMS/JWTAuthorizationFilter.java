package io.nikita.security.securityMS;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
   @Autowired
    JwtUtil jwtUtil;
   @Autowired
    ObjectMapper mapper;

   UserService userDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Map<String,Object> errors = new HashMap<>();
//
//        try{
//            String requestUrl =  request.getRequestURL().toString();
//            if(!requestUrl.contains("/v1/auth/login")) {
//                String accessToken = jwtUtil.resolveToken(request);
//                if (accessToken.isEmpty() || accessToken == null) {
//                    filterChain.doFilter(request, response);
//                    return;
//                }
//                Claims claims = jwtUtil.resolveClaims(request);
//                if (claims != null && jwtUtil.validateClaims(claims)) {
//                    String name = claims.getSubject();
//                    Authentication authentication = new UsernamePasswordAuthenticationToken(name, "", new ArrayList<>());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                }
//            }
//        }catch (Exception e){
//            errors.put("message", "Authentication Error");
//            errors.put("details",e.getMessage());
//            response.setStatus(HttpStatus.FORBIDDEN.value());
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//            mapper.writeValue(response.getWriter(), errors);
//
//        }
//        filterChain.doFilter(request, response);
//    }
//

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//        if (authHeader != null && authHeader.startsWith("Bearer")) {
//            token = authHeader.substring(7);
//            username = jwtUtil.extractUsername(token);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (jwtUtil.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }


//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String header = req.getHeader("Authorization");
//        String username = null;
//        if (header != null && header.startsWith("Bearer")) {
//            String authToken = header.replace("Bearer","");
//            username = jwtUtil.extractUsernameFromToken(authToken);
//        } else {
//            logger.warn("couldn't find bearer string, will ignore the header");
//        }
//        if (StringUtils.hasText(username)) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//            logger.info("authenticated user " + username + ", setting security context");
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        chain.doFilter(req, res);
//    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtUtil.getToken(request) ;

        if (token!=null && jwtUtil.validateToken(token))
        {
            String name = jwtUtil.extractUsername(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
//                log.info("authenticated user with email :{}", email);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        filterChain.doFilter(request,response);
    }
}