package online.partyrun.springsecurityauthorizationmanager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(AuthorizationRegistry.class)
public class AuthorizationRegistryConfig {
    @Bean
    public AuthorizationRegistry authorizationRegistry() {
        return new DefaultAuthorizationRegistry();
    }
}
