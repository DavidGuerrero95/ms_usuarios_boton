package co.com.udea.api;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.contacts.Contacts;
import co.com.udea.usecase.RegisterUseCase;
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

    @PostMapping(path = "/save", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Guardar contactos", description = "Servicio para guardar contactos")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Almacenamiento realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo almacenar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData saveContacts(@RequestBody @Validated Contacts contacts) {
        return null;
    }

}
