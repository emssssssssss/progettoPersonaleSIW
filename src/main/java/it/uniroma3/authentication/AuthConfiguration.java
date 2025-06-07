package it.uniroma3.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    private static final String ROLE_STAFF = "STAFF";
    private static final String ROLE_VISITATORE = "VISITATORE";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/", "/index", "/login", "/register", "/css/**", "/images/**", "/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .requestMatchers("/admin/**").hasAuthority(ROLE_STAFF)
                .requestMatchers("/staff/fasce/**").hasAuthority("STAFF")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/default", true)
                .failureUrl("/loginError")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT email, password, true FROM utente WHERE email = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT email, ruolo FROM utente WHERE email = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
