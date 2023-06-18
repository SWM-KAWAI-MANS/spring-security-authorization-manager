package online.partyrun.springsecurityauthorizationmanager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class DefaultAuthorizationRegistry implements AuthorizationRegistry {
    @Override
    public AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry match(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry r) {
        return r.anyRequest()
                .authenticated();
    }
}
