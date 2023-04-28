package co.com.udea.usecase;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.common.RoleIds;
import co.com.udea.model.kafka.Email;
import co.com.udea.model.kafka.gateways.KafkaPort;
import co.com.udea.model.register.Register;
import co.com.udea.model.register.gateways.RegisterRepository;
import co.com.udea.model.util.Constants;
import co.com.udea.utils.ResponseMethods;
import co.com.udea.utils.UtilFunctions;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RegisterUseCase {

    private final RegisterRepository registerRepository;
    private final KafkaPort kafkaPort;


    public ResponseData sendMessage(Register register) {
        Long minutes = new Date().getTime();
        Email email = new Email(register.getUsername(), register.getEmail(), register.getCode());
        register.setCellPhone(register.getCellPhone());
        register.setCode(String.valueOf((int) (100000 * Math.random() + 99999)));
        register.setPassword(UtilFunctions.encode(register.getPassword()));
        register.setMinutes(minutes);
        register.setRoles(List.of(RoleIds.valueOf("citizen")));
        try {
            ResponseData successResponseData = ResponseMethods.getSuccessResponseData(registerRepository.save(register),
                    HttpStatus.OK);
            kafkaPort.sendMessage(email);
            return successResponseData;
        } catch (JsonProcessingException e) {
            return ResponseMethods.getErrorResponseData(e, HttpStatus.BAD_REQUEST, Constants.REGISTRO_MENSAJE_ERROR);
        }

    }

}
