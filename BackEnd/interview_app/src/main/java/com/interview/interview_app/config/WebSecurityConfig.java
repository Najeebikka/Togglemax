// package com.interview.interview_app.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.List;

// import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration
// public class WebSecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .cors(withDefaults())
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                     "/api/login","/api/email-templates/**","/api/interview-results/upload","/api/interview-questions/interview-token/**"
//                     ).permitAll()
//                 .anyRequest().authenticated()
//             )
//             .httpBasic(withDefaults());

//         return http.build();
//     }

//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration config = new CorsConfiguration();
//         config.setAllowedOrigins(List.of("http://localhost:3000","http://backend:8080"));
//         config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         config.setAllowedHeaders(List.of("*"));
//         config.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", config);
//         return source;
//     }

//     // ✅ Add this method here to define an in-memory user
//     @Bean
//     public UserDetailsService users() {
//         UserDetails user = User.withDefaultPasswordEncoder()
//             .username("admin")
//             .password("admin")
//             .roles("USER")
//             .build();

//         return new InMemoryUserDetailsManager(user);
//     }
// }
