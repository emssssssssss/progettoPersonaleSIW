package it.uniroma3.progettopersonalesiw1;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeneraHashTest {

    @Test
    public void generaHashPassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hash generato: " + hashedPassword);
        
    }
}

//$2a$10$2T/fTS93MRzu6HziZOgHz.D7/uHnLNPUq3rz4sC/D1rWvOVgWvTvu
//"$2a$10$iBSjllf96EgaYGnCxZFX8e4ulsiHWhHsWxBMjsq17SgTvqoBSnLfq"
//$2a$10$wj28cx6B1jjuP2BYrpIGOuHBVS15lwouZ5SB8VfqWPDQW2c3fw7du


