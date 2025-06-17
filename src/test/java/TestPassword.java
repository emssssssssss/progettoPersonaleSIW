

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPassword {

    @Test
    public void testPasswordMatch() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordInChiaro = "password123";
        String hashSalvato = "$2a$10$XKtkhBOYbJJHOcOkgjew8e7MsHKxpY6c3AGnTuCAZ/pmQQ.8G2Jbe";

        boolean match = encoder.matches(passwordInChiaro, hashSalvato);

        System.out.println("La password corrisponde? " + match);
        assertTrue(match, "La password deve corrispondere all'hash");
    }
}
