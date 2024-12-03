package com.spring.practiceSpring.configuration;

import com.spring.practiceSpring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // block -4 UserDetailService , here UserDetailsServiceImpl class is implemented to UserDetailService class which tell us or load user details. check UserDetailsServiceImpl class which is
    // inside in service folder.
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    // block-3 Authentication provider , it will authenticate or check weather our username and password is correct or not. ... if we not write this bean Spring boot automatically have a
    // AuthenticationProvider class which authenticate the username and password but yeah we can make our own authenticationProvider class.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Ensure password is encoded
        return authProvider;
    }


    // block-1 Authentication filter ,
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/login" , "/api/students","/api/students/student-data","/css/**", "/js/**", "/images/**").permitAll()// Allow all endpoints under /api/students
                .anyRequest().authenticated());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.formLogin(form -> form
                .loginPage("/login") // Custom login page URL
                .defaultSuccessUrl("/api/students/welcome")
                .permitAll()
        );

        http.logout(LogoutConfigurer::permitAll);


        return http.build();
    }


    // block-2A authentication Manager ,, authentication manager ka kaam bas itana sa hai ki bhai jo mere pass cridential aa rahe hai authenciation provider se uska karna kya kai ...
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    // block -2B , this configureGlobal will tell us that we can use that UserDetailsService class , thats it.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService); // Tell Spring to use UserDetailsServiceImpl for user authentication
    }


}
