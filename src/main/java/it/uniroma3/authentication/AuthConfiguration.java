/*package it.uniroma3.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.uniroma3.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Inietta il tuo custom success handler
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http.csrf(csrf -> csrf.disable());
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.GET,
                                "/", "/index", "/login", "/register", "/registrazione",
                                "/eventi", "/evento/**", "/opere", "/opera/**", "/homepage", "/fasce", "/fasce/**",
                                "/visite", "/uploads/images/**",
                                "/artisti", "/artista/**", "/css/**", "/images/**", "/error")
                        .permitAll()
                        .requestMatchers(HttpMethod.HEAD, "/uploads/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .requestMatchers("/admin/**", "/staff/fasce/**").hasAuthority("ROLE_STAFF")
                        .requestMatchers(HttpMethod.POST, "/evento/rimuovi-opera/**", "/evento/salva").hasAuthority("ROLE_STAFF")
                        .requestMatchers("/eventoNuovo").hasAuthority("ROLE_STAFF")
                        .requestMatchers("/eventoModifica/**").hasAuthority("ROLE_STAFF")


                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(successHandler) // Usa il custom handler qui
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

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public class TomcatConfig {

        @Bean
        public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
            return factory -> factory.addConnectorCustomizers(connector -> {
                connector.setProperty("maxParameterCount", "10000");
                connector.setProperty("maxPostSize", String.valueOf(10 * 1024 * 1024));
            });
        }

    }
} */
package it.uniroma3.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import it.uniroma3.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // Tutto libero per ora
                )
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
