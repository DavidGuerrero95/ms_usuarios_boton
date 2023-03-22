package co.com.udea.api;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.contacts.Contacts;
import co.com.udea.model.organizations.Organizations;
import co.com.udea.usecase.OrganizationsUseCase;
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
@RequestMapping(value = "/organizations", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrganizationsRest {

    private final OrganizationsUseCase useCase;

    @PostMapping(path = "/save", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Guardar Organizacion", description = "Servicio para guardar Organizacion")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Almacenamiento realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo almacenar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseData saveOrganizations(@RequestBody @Validated Organizations organizations) {
        return useCase.save(organizations);
    }

    @PutMapping(path = "/edit", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Editar Organizacion", description = "Servicio para editar Organizacion")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Edicion realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo editar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData editOrganizations(@RequestBody @Validated Organizations organizations) {
        return useCase.edit(organizations);
    }

    @GetMapping(path = "/list", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Listar Organizacion", description = "Servicio para Listar Organizacion")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Listar realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo Listar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData listOrganizations() {
        return useCase.findAll();
    }

    @GetMapping(path = "/view", produces = "application/json;charser=UTF-8")
    @Operation(summary = "ver Organizacion", description = "Servicio para ver Organizacion")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "ver realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo ver la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData viewOrganizations(@RequestParam("id") String id) {
        return useCase.getOrganizationsId(id);
    }

    @GetMapping(path = "/delete", produces = "application/json;charser=UTF-8")
    @Operation(summary = "Eliminar Organizacion", description = "Servicio para eliminar Organizacion")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "eliminacion realizado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se pudo eliminar la informaci\u00f3n")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseData deleteOrganizations(@RequestParam("id") String id) {
        return useCase.deleteOrganizationsId(id);
    }

}
