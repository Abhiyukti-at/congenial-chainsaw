package io.nikita.security.securityMS;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class MyController {

    @Autowired
    UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private JwtUtil jwtUtil;

    @GetMapping("/details")
    public List<User> getUserDetails(){
        User r = userRepository.findByEmail("nikita@gmail.com");
        List<User> result = userRepository.findAll();
        return result;
    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequest loginReq)  {
//
//        try {
//            Authentication authentication =
//                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getName(), loginReq.getPassword()));
//            String email = authentication.getName();
//            User user = new User(email,"");
//            String token = jwtUtil.createToken(user);
//            LoginRes loginRes = new LoginRes(email,token);
//
//            return ResponseEntity.ok(loginRes);
//
//        }catch (BadCredentialsException e){
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }catch (Exception e){
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }
//    }
}