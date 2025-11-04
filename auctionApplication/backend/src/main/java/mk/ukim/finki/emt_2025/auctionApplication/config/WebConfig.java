package mk.ukim.finki.emt_2025.auctionApplication.config;

import mk.ukim.finki.emt_2025.auctionApplication.web.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import java.util.List;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class WebConfig {

    private final JwtFilter jwtFilter;

    public WebConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**",
                                "/api/users/register",
                                "/api/users/login",
                                "/api/items/{id}/image",
                                "/ws/**",
                                "/topic/**",
                                "/ws/info**"
                        ).permitAll()
                        .requestMatchers("/api/users/me").authenticated()
                        // customer endpoints
                        .requestMatchers(
                                "/api/items",
                                "/api/items/{id}",
                                "/api/users",
                                "/api/users/{id}",
                                "/api/auctions",
                                "/api/items/{id}/createAuction",
                                "/api/auctions/{id}",
                                "/api/auctions/{id}/organizer",
                                "/api/auctions/{id}/start",
                                "/api/auctions/{id}/cancel",
                                "/api/auctions/{id}/finish",
                                "/api/auctions/{auctionId}/addVisitor",
                                "/api/auctions/{id}/lastOffer",
                                "/api/auctions/{id}/addOffer",
                                "/api/users/byUsername/{username}"
                        ).hasRole("CUSTOMER")
                        // owner endpoints
                        .requestMatchers(
                                "/api/items/add",
                                "/api/items/update/{itemId}",
                                "/api/users/update/{id}"
                        ).hasRole("OWNER")
                        // admin endpoints
                        .anyRequest().hasRole("ADMIN")     //delete Item i delete User
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Use allowedOriginPatterns instead of allowedOrigins
        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:3000",
                "http://127.0.0.1:3000",
                "ws://localhost:3000"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("OWNER")
                .role("OWNER").implies("CUSTOMER")
                .build();
    }

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}