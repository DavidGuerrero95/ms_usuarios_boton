package co.com.udea.api;

import co.com.udea.model.contacts.Contacts;
import co.com.udea.usecase.contacts.ContactsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ContactsRest {

    private final ContactsUseCase useCase;

    @GetMapping("/listar/{username}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Contacts> listarContactos(@PathVariable("username") String username) {
        return useCase.listContacts(useCase.getUsername(username));
    }

    @PostMapping("/crear/{username}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String crearContactoUsuario(@PathVariable("username") String username, @RequestBody @Validated Contacts contacts) throws IOException {
            if (useCase.save(useCase.getUsername(username), contacts)) return "Contacto creado correctamente";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la creación de contactos");
    }

    @PutMapping("/editar/{username}/email/{email}/cellPhone/{cellPhone}")
    @ResponseStatus(code = HttpStatus.OK)
    public String editarContacto(@PathVariable("username") String username, @PathVariable("email") String email,
                                 @PathVariable("cellPhone") String cellPhone, @RequestBody Contacts contacts) throws IOException {
        String userId = useCase.getUserById(username);

        if (useCase.update(userId, email, cellPhone, contacts))
            return "Contacto editado correctamente";
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la edición de contactos");
    }

    @DeleteMapping("/eliminar/{username}/email/{email}/cellPhone/{cellPhone}")
    @ResponseStatus(code = HttpStatus.OK)
    public String eliminarContacto(@PathVariable("username") String username, @PathVariable("email") String email,
                                   @PathVariable("cellPhone") String cellPhone) throws IOException {
        String userId = useCase.getUserById(username);

        if (useCase.delete(userId, email, cellPhone)) return "Contacto eliminado correctamente";
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la eliminacion de contactos");
    }


}
