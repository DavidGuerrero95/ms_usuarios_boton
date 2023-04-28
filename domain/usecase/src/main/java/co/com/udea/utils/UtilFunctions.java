package co.com.udea.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UtilFunctions {
    static PasswordEncoder encoder;

    public static String encode(String password) {
        return encoder.encode(password);
    }

}
