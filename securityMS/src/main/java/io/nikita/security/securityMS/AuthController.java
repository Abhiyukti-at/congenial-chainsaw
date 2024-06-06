package io.nikita.security.securityMS;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/auth/login",method = RequestMethod.GET)
    public ResponseEntity login(@RequestBody LoginRequest loginReq)  {

    try {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getName(), loginReq.getPassword()));
        String email = authentication.getName();
        User user = new User(email,"");
        String token = jwtUtil.createToken(user);
        LoginRes loginRes = new LoginRes(email,token);

        return ResponseEntity.ok(loginRes);

    }catch (BadCredentialsException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }catch (Exception e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

}