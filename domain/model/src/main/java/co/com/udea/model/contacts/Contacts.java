package co.com.udea.model.contacts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Contacts {
    private String id;
    private String userId;
    @Pattern(regexp = "[A-Za-z ]+", message = "firstName solo permite A-Z,a-z y ' '.")
    @Size(max = 36, message = "El primer nombre debe tener entre 0 y 36 caracteres")
    private String firstName;
    @Pattern(regexp = "[A-Za-z ]+", message = "Solo letras")
    @Size(max = 36, message = "El apellido debe tener entre 0 y 36 caracteres")
    private String lastName;
    @NotNull(message = "Email no puede ser nulo")
    @Size(min = 6, max = 50, message = "El email debe tener entre 6 y 50 caracteres")
    @Pattern(regexp = "[^ ]*+", message = "Caracter: ' ' (Espacio en blanco) invalido")
    @Email(message = "Debe ser un email valido")
    private String email;
    @NotNull(message = "Numero de celular no puede ser nulo")
    @Pattern(regexp = "[0-9]+", message = "Solo numeros")
    @Size(min = 8, max = 12, message = "Tamaño de celular es incorrecto")
    private String cellPhone;
}
