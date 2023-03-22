package co.com.udea.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UtilFunctions {
    PasswordEncoder encoder;

    public String encode(String password) {
        return encoder.encode(password);
    }

}
