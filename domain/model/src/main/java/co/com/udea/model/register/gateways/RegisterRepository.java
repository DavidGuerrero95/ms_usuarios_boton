package co.com.udea.model.register.gateways;

import co.com.udea.model.register.Register;
import org.springframework.http.HttpStatus;

public interface RegisterRepository {
    Register save(Register register);
}
