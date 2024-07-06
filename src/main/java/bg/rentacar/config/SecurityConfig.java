package bg.rentacar.config;

import bg.rentacar.web.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizedRequest -> authorizedRequest
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/", "/login", "/register", "/fleet",
                                        "/login-error", "/api/cars/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login");
                    formLogin.usernameParameter("username");
                    formLogin.passwordParameter("password");
                    formLogin.defaultSuccessUrl("/", true);
                    formLogin.failureUrl("/login-error");
//                    formLogin.failureHandler(authenticationFailureHandler());
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
                    logout.invalidateHttpSession(true);
                })
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Enables CSRF with a cookie-based repository
//                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return (request, response, exception) -> {
//            logger.info("Authentication failed: {}", exception.getMessage());
//            response.sendRedirect("/login-error");
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
