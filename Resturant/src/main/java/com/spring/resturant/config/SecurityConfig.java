package com.spring.resturant.config;

import com.spring.resturant.sitting.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtTokenHandler jwtTokenHandler
    ) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // 🔓 Swagger MUST be public
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/webjars/**"
                        ).permitAll()

                        // 🔓 Auth endpoints
                        .requestMatchers("/auth/**").permitAll()

                        // 🔓 error page
                        .requestMatchers("/error").permitAll()

                        // 🔒 ADMIN endpoints
                        .requestMatchers("/order/get-all").hasRole("ADMIN")
                        .requestMatchers("/contact/get-all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/order/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/contact/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/order/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/order/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/order/get-by-id").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                        // 🔒 everything else requires auth
                        .anyRequest().authenticated()
                )

                // JWT filter
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenHandler),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://localhost:8333"
        ));

        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        config.setAllowedHeaders(List.of("*"));

        config.setExposedHeaders(List.of("Authorization"));

        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}