package co.com.udea.api;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.contacts.Contacts;
import co.com.udea.usecase.ContactsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ContactsRest {

    private final ContactsUseCase useCase;

    @PostMapping(path = "/save", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Guardar contactos", description = "Servicio para guardar contactos")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Almacenamiento realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo almacenar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData saveContacts(@RequestBody @Validated Contacts contacts) {
        return useCase.save(contacts);
    }

    @PutMapping(path = "/update", produces = "application/json;charser=UTF-8")
    @Operation(summary = "actualizar contactos", description = "Servicio para actualizar contactos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "actualizaci\u00f3n realizado" +
            " correctamente"), @ApiResponse(responseCode = "204", description = "No se pudo actualizar la " +
            "informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData updateContacts(@RequestBody @Validated Contacts contacts) {
        return useCase.update(contacts);
    }

    @PutMapping(path = "/delete", produces = "application/json;charser=UTF-8")
    @Operation(summary = "eliminar contacto", description = "Servicio para eliminar contacto")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "eliminaci\u00f3n realizado" +
            " correctamente"), @ApiResponse(responseCode = "204", description = "No se pudo eliminar la " +
            "informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData deleteContacts(@RequestParam("id") String id) {
        return useCase.delete(id);
    }

    @GetMapping(path = "/list", produces = "application/json;charser=UTF-8")
    @Operation(summary = "listar contactos", description = "Servicio para listar contactos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "listado realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo listar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData listContacts(@RequestParam("userId") String userId) {
        return useCase.list(userId);
    }

    @GetMapping(path = "/view", produces = "application/json;charser=UTF-8")
    @Operation(summary = "ver contactos", description = "Servicio para ver contactos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "visualizaci\u00f3n realizada " +
            "correctamente"), @ApiResponse(responseCode = "204", description = "No se pudo visualizar" +
            " la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData viewContacts(@RequestParam("id") String id) {
        return useCase.view(id);
    }

}
