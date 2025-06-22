package it.uniroma3.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class AuthConfiguration {

        @Autowired
        private DataSource dataSource;

        private static final String ROLE_STAFF = "ROLE_STAFF";
        private static final String ROLE_VISITATORE = "ROLE_VISITATORE";

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers(HttpMethod.GET,
                                                                "/", "/index", "/login", "/register", "/Registrazione",
                                                                "/eventi", "/evento/**", "fasce", "/fascia/**",
                                                                "/css/**", "/images/**", "/error")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST, "/login", "/register", "Registrazione").permitAll()
                                                .requestMatchers("/admin/**").hasAuthority(ROLE_STAFF)
                                                .requestMatchers("/staff/fasce/**").hasAuthority(ROLE_STAFF)
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .usernameParameter("email") // <-- nome del campo input del form (deve
                                                                            // essere "email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/default", true)
                                                .failureUrl("/loginError")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                return http.build();
        }

        @Bean
        @DependsOnDatabaseInitialization
        JdbcUserDetailsManager users(DataSource dataSource) {
                JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

                // Login solo tramite email
                manager.setUsersByUsernameQuery(
                                "SELECT email, password, true FROM utente WHERE email = ?");

                manager.setAuthoritiesByUsernameQuery(
                                "SELECT email, CONCAT('ROLE_', ruolo) AS authority FROM utente WHERE email = ?");

                return manager;
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
