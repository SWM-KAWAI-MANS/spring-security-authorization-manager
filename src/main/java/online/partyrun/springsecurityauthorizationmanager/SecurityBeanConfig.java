package online.partyrun.springsecurityauthorizationmanager;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import online.partyrun.jwtmanager.JwtExtractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(SecurityConfig.class)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityBeanConfig {
    String[] exclusions;
    JwtExtractor extractor;

    public SecurityBeanConfig(
            @Value("${auth.filter.exclusions}") String[] exclusions, JwtExtractor extractor) {
        this.exclusions = exclusions;
        this.extractor = extractor;
    }

    @Bean
    @ConditionalOnMissingBean(AuthorizationRegistry.class)
    public AuthorizationRegistry authorizationRegistry() {
        return new DefaultAuthorizationRegistry();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(extractor, filterExclusionValidator());
    }

    @Bean
    public FilterExclusionValidator filterExclusionValidator() {
        return new FilterExclusionValidator(exclusions);
    }
}
