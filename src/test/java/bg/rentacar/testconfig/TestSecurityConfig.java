package bg.rentacar.testconfig;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizedRequest -> authorizedRequest
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .anyRequest().permitAll()
        )
                .formLogin(formLogin ->{
                    HttpSecurity disable = formLogin.disable();
                });

        return http.build();
    }
}
