package Folder.into.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Folder.into.Domain.Users;
import Folder.into.Repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository usersRepository;

    private static final String AUTH_TOKEN_HEADER_NAME = "apikey";
    private static final String AUTH_TOKEN = "123456";

 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // API Key Authentication Filter
        APIKeyAuthFilter filter = new APIKeyAuthFilter(AUTH_TOKEN_HEADER_NAME);
        filter.setAuthenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String principal = (String) authentication.getPrincipal();
                if (!AUTH_TOKEN.equals(principal)) {
                    throw new BadCredentialsException("The API key was not found or not the expected value.");
                }
                authentication.setAuthenticated(true);
                return authentication;
            }
        });

        // Security Configuration
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() 
                .requestMatchers("/bookings/**", "/rooms/**")
                    .hasAnyRole("STUDENT", "STAFF", "TEACHER", "HEAD_DEPARTMENT", "DIRECTOR") 
                .requestMatchers("/approvals/**")
                    .hasAnyRole("TEACHER", "HEAD_DEPARTMENT", "DIRECTOR") 
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true) 
                .failureUrl("/login?error=true")
                .permitAll() 
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") 
                .permitAll() 
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) 
            .headers(headers -> headers
                .frameOptions().sameOrigin()
            );

        return http.build();
    }

    
     // UserDetailsService for loading user information from the database.
   
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Fetch user from database by username
            Users user = usersRepository.findByUsersName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Return UserDetails with roles
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsersName())
                    .password("{noop}" + user.getUsersPassword())
                    .roles(user.getUsersType().name()) 
                    .build();
        };
    }


    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); 
    }
}
