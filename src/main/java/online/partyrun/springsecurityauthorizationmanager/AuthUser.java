package online.partyrun.springsecurityauthorizationmanager;

import online.partyrun.jwtmanager.dto.JwtPayload;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class AuthUser extends UsernamePasswordAuthenticationToken {
    public AuthUser(JwtPayload payload) {
        super(payload.id(), null, toAuthority(payload.roles()));
    }

    private static List<SimpleGrantedAuthority> toAuthority(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }
}
