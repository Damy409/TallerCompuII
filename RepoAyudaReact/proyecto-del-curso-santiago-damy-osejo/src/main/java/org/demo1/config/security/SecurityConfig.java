package org.demo1.config.security;

import org.demo1.config.filters.CustomAuthenticationFilter;
import org.demo1.config.managers.CustomAuthenticationManager;
import org.demo1.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
        @Lazy CustomAuthenticationManager customAuthenticationManager,
        JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.customAuthenticationManager = customAuthenticationManager;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return customAuthenticationManager;
    }

    @Bean
    public SecurityFilterChain customAuthFilterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());

        return http
                .securityMatcher("/customAuth/**")
                .authenticationManager(authenticationManager())
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .cors(with -> with
                        .configurationSource(corsConfigurationSource())
                )
                .build();
    }

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) 
                .cors(with -> with
                        .configurationSource(corsConfigurationSource())
                )
                .securityMatcher("/**")
                .authenticationManager(authenticationManager())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // JWT filter
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
