package com.karolkusper.KINEMA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(conf -> conf.disable())
//                .cors(c->c.configurationSource(corsConfigurationSource()))
                .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
//            .requestMatchers("/api/v1/auth/**").permitAll()
//           .requestMatchers("/error").permitAll() DO POPRAWY
                    .requestMatchers("/api/v1/auth/**", "/error").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/movies").hasAnyRole("CLIENT", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/screenings").hasAnyRole("CLIENT", "ADMIN")
                    .requestMatchers("/api/screenings/**").hasRole("ADMIN")
                    .requestMatchers("/api/movies/**").hasRole("ADMIN")
            .anyRequest().authenticated())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

}
