package co.com.udea.api;

import co.com.udea.model.common.RequestPageable;
import co.com.udea.model.common.ResponseData;
import co.com.udea.model.users.Users;
import co.com.udea.usecase.UsersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UsersRest {

    private final UsersUseCase useCase;

    @GetMapping(path = "/list", produces = "application/json;charser=UTF-8")
    @Operation(summary = "listar todos los usuarios", description = "Servicio para listar usuarios")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "listado realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo listar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData listUsers(@Validated @RequestBody RequestPageable requestPageable) {
        return useCase.list(requestPageable);
    }

    @PutMapping(path = "/edit", produces = "application/json;charser=UTF-8")
    @Operation(summary = "editar usuario", description = "Servicio para editar usuarios")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "edici\u00f3n realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo editar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData editUsers(@Validated @RequestBody Users users) {
        return useCase.edit(users);
    }

    @GetMapping(path = "/view", produces = "application/json;charser=UTF-8")
    @Operation(summary = "ver usuario", description = "Servicio para ver usuarios")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "visualizaci\u00f3n realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo ver la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData viewUser(@RequestParam("id") String id) {
        return useCase.view(id);
    }

    @DeleteMapping(path = "/delete", produces = "application/json;charser=UTF-8")
    @Operation(summary = "eliminar usuario", description = "Servicio para eliminar usuarios")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Eliminaci\u00f3n realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo eliminar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData deleteUser(@RequestParam("id") String id) {
        return useCase.delete(id);
    }

    @PostMapping(path = "/create-first", produces = "application/json;charser=UTF-8")
    @Operation(summary = "crear primer usuario", description = "Servicio para crear primer usuario")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Creaci\u00f3n primer usuario"),
            @ApiResponse(responseCode = "204", description = "No se pudo crear la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData createFirstUser() {
        return useCase.createFirstUser();
    }

}
