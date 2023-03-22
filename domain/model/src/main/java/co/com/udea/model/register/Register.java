package co.com.udea.model.register;

import co.com.udea.model.common.RoleIds;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Register {

    @JsonIgnore
    private String id;

    @NotBlank(message = "Username no puede ser nulo")
    @Pattern(regexp = "[A-Za-z0-9_.-]+", message = "Solo se permite:'_' o '.' o '-'")
    @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "Password no puede ser nulo")
    @Pattern(regexp = "[A-Za-z0-9@_.&$-]+", message = "Solo se permiten letras, numeros y los caracteres especiales" +
            "@_.-&$")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;

    @NotBlank(message = "Email no puede ser nulo")
    @Size(min = 6, max = 50, message = "El email debe tener entre 6 y 50 caracteres")
    @Pattern(regexp = "[^ ]*+", message = "Caracter: ' ' (Espacio en blanco) invalido")
    @Email(message = "Debe ser un email valido")
    private String email;

    @Pattern(regexp = "[0-9+]+", message = "Solo numeros")
    @Size(min = 9, max = 15, message = "El celular debe tener entre 9 y 15 caracteres")
    private String cellPhone;
    private String code;
    private Long minutes;
    private List<RoleIds> roles;

}
