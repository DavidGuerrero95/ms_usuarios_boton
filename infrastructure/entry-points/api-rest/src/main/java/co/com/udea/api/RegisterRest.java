package co.com.udea.api;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.contacts.Contacts;
import co.com.udea.model.register.Register;
import co.com.udea.usecase.RegisterUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RegisterRest {

    private final RegisterUseCase useCase;

    @PostMapping(path = "/send", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Enviar mensaje de confirmacion", description = "Servicio para enviar mensaje")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Mensaje realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo enviar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData sendMessage(@RequestBody @Validated Register register) throws JsonProcessingException {
        return useCase.sendMessage(register);
    }

}
