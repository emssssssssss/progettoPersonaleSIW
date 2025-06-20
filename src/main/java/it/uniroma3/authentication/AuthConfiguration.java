package it.uniroma3.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.uniroma3.service.CustomUserDetailsService;

@Configuration
public class AuthConfiguration {

        @Autowired
        private DataSource dataSource;
        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        private static final String ROLE_STAFF = "ROLE_STAFF";
        private static final String ROLE_VISITATORE = "ROLE_VISITATORE";

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers(HttpMethod.GET,
                                                                "/", "/index", "/login", "/register", "/registrazione",
                                                                "/eventi", "/evento/**", "/opere", "/opera/**", "/homepage",  
                                                                "/artisti", "/artista/**", "/css/**", "/images/**", "/error")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST, "/login", "/register", "/registrazione").permitAll()
                                                .requestMatchers("/admin/**").hasAuthority(ROLE_STAFF)
                                                .requestMatchers("/staff/fasce/**").hasAuthority(ROLE_STAFF)
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .usernameParameter("email") // <-- nome del campo input del form (deve
                                                                            // essere "email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/homepage", true)
                                                .failureUrl("/loginError")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/homepage")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                return http.build();
        }

         // Rimosso JdbcUserDetailsManager

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
