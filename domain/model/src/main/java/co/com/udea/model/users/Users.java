package co.com.udea.model.users;

import co.com.udea.model.common.AppIds;
import co.com.udea.model.common.DocType;
import co.com.udea.model.common.GenderType;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Users {

    private String id;
    @Pattern(regexp = "[A-Za-z0-9_.-]+", message = "Solo se permite:'_' o '.' o '-'")
    @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "Password no puede ser nulo")
    @Pattern(regexp = "[A-Za-z0-9@_.&$-]+", message = "Solo se permiten letras, numeros y los caracteres especiales" +
            "@_.-&$")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;
    @Size(min = 6, max = 50, message = "El email debe tener entre 6 y 50 caracteres")
    @Pattern(regexp = "[^ ]*+", message = "Caracter: ' ' (Espacio en blanco) invalido")
    @Email(message = "Debe ser un email valido")
    private String email;
    @Pattern(regexp = "[0-9+]+", message = "Solo numeros")
    @Size(min = 9, max = 15, message = "El celular debe tener entre 9 y 15 caracteres")
    private String cellPhone;
    @JsonIgnore
    private List<RoleIds> roles;
    @JsonIgnore
    private Integer attempts;
    @JsonIgnore
    private HashMap<AppIds, RoleIds> activeRole;
    @JsonIgnore
    private Integer verificationCode;
    @JsonIgnore
    private Boolean accountEnabled;
    @Pattern(regexp = "[A-Za-z ]+", message = "Solo letras")
    @Size(max = 36, message = "El primer nombre debe tener entre 0 y 36 caracteres")
    private String firstName;

    @Pattern(regexp = "[A-Za-z]+", message = "Solo letras")
    @Size(max = 36, message = "El primer nombre debe tener entre 0 y 36 caracteres")
    private String lastName;
    private DocType docType;
    private String documentId;
    private LocalDateTime birthDate;
    private GenderType gender;
    private String address;
    private List<String> geoAddress;
    private String department;
    private String city;
    private String country;
    private String neighborhood;
    private String zipCode;
    private String landLine;
    private String economicActivity;
    private List<String> economicData;
    private List<String> interest;
    private Boolean familyHead;
    private LocalDateTime registrationDate;
    private Boolean firstSeason;
    private String colour;
}
