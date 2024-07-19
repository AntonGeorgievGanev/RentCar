package bg.rentacar.config;

import bg.rentacar.model.enums.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                                        "/login-error", "/customers-review").permitAll()
                                .requestMatchers("/add-car", "/add-extra","/manage-fleet",
                                        "/manage-fleet/**", "/manage-orders", "/manage-orders/**", "/users-info").hasRole(Role.ADMIN.name())
                                .requestMatchers("/my-reviews/delete/**").hasRole(Role.USER.name())
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
//                .csrf(AbstractHttpConfigurer::disable)
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
