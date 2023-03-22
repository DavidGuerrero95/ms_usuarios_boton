package co.com.udea.mongo.register;

import co.com.udea.model.register.Register;
import co.com.udea.model.register.gateways.RegisterRepository;
import co.com.udea.mongo.helper.AdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RegisterDataAdapter
        extends AdapterOperations<Register, RegisterData, String, RegisterDataRepository>
        implements RegisterRepository {
    public RegisterDataAdapter(RegisterDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Register.class));
    }



}
