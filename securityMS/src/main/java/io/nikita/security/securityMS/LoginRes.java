package io.nikita.security.securityMS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRes {
    private String email;
    private String token;
}