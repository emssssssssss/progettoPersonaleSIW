package it.uniroma3.progettopersonalesiw1;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeneraHashTest {

    @Test
    public void generaHashPassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hash generato: " + hashedPassword);
    }
}
