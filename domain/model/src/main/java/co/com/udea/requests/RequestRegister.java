package co.com.udea.requests;

import co.com.udea.model.common.DocType;
import co.com.udea.model.common.GenderType;
import co.com.udea.model.common.annotations.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestRegister {

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
    @NotBlank(message = "first name no puede ser nulo")
    @NotNull(message = "first name no puede ser nulo")
    @Size(max = 36, message = "El nombre debe tener entre 0 y 36 caracteres")
    private String firstName;
    @NotBlank(message = "last name no puede ser nulo")
    @NotNull(message = "last name no puede ser nulo")
    @Size(max = 36, message = "El lastName entre 0 y 36 caracteres")
    private String lastName;
    @EnumValue(enumClass = DocType.class)
    @NotBlank(message = "docType no puede ser nulo")
    @NotNull(message = "docType no puede ser nulo")
    private DocType docType;
    @NotBlank(message = "documentId no puede ser nulo")
    @NotNull(message = "documentId no puede ser nulo")
    @Size(min = 5, max = 15, message = "El documentId 5 y 15 caracteres")
    private String documentId;
    @NotNull(message = "birthDate no puede ser nulo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;
    @EnumValue(enumClass = GenderType.class)
    @NotBlank(message = "gender no puede ser nulo")
    @NotNull(message = "gender no puede ser nulo")
    private GenderType gender;
    @NotBlank(message = "address no puede ser nulo")
    @NotNull(message = "address no puede ser nulo")
    @Size(min = 5, max = 100, message = "El address 5 y 15 caracteres")
    private String address;
    @NotBlank(message = "geoAddress no puede ser nulo")
    @NotNull(message = "geoAddress no puede ser nulo")
    @Size(min = 2, max = 2, message = "geoAddress debe tener dos elementos")
    private List<String> geoAddress;
    @NotBlank(message = "country no puede ser nulo")
    @NotNull(message = "country no puede ser nulo")
    @Size(min = 1, max = 195, message = "country debe tener hasta 195 elementos")
    private Integer country;
    @NotBlank(message = "department no puede ser nulo")
    @NotNull(message = "department no puede ser nulo")
    @Size(min = 1, max = 195, message = "department debe tener hasta 195 elementos")
    private Integer department;
    @NotBlank(message = "city no puede ser nulo")
    @NotNull(message = "city no puede ser nulo")
    @Size(min = 1, max = 4000, message = "city debe tener hasta 4000 elementos")
    private Integer city;
    @NotBlank(message = "commune no puede ser nulo")
    @NotNull(message = "commune no puede ser nulo")
    @Size(min = 1, max = 300, message = "commune debe tener hasta 300 elementos")
    private Integer commune;
    @NotBlank(message = "neighborhood no puede ser nulo")
    @NotNull(message = "neighborhood no puede ser nulo")
    @Size(min = 1, max = 300, message = "neighborhood debe tener hasta 300 elementos")
    private Integer neighborhood;
    @NotBlank(message = "transportMeans no puede ser nulo")
    @NotNull(message = "transportMeans no puede ser nulo")
    @Size(min = 1, max = 50, message = "transportMeans debe tener hasta 50 elementos")
    private Integer transportMeans;
    @NotBlank(message = "economicActivity no puede ser nulo")
    @NotNull(message = "economicActivity no puede ser nulo")
    @Size(min = 1, max = 500, message = "economicActivity debe tener hasta 500 elementos")
    private Integer economicActivity;
    @NotBlank(message = "familyHead no puede ser nulo")
    @NotNull(message = "familyHead no puede ser nulo")
    private Boolean familyHead;
    @NotBlank(message = "colour no puede ser nulo")
    @NotNull(message = "colour no puede ser nulo")
    @Size(min = 1, max = 50, message = "colour debe tener hasta 500 elementos")
    private Integer colour;

}
