package co.com.udea.model.organizations;

import co.com.udea.model.common.OrgType;
import co.com.udea.model.common.annotations.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Organizations {

    private String id;
    @Size(max=200, message = "El nombre de la organización no puede tener mas de 200 letras")
    private String orgName;
    @EnumValue(enumClass = OrgType.class)
    private OrgType type;
    @Size(max=200, message = "descripción no puede tener mas de 200 letras")
    private String description;
    private String address;
    @Size(min = 2, max = 2, message = "La lista debe tener exactamente dos elementos")
    private List<String> geoAddress;
    @Pattern(regexp = "[A-Za-z ]+", message = "Solo letras")
    @Size(max = 36, message = "El apellido debe tener entre 0 y 36 caracteres")
    private String contactName;
    @Pattern(regexp = "[0-9]+", message = "Solo numeros")
    @Size(min = 8, max = 12, message = "Tamaño de celular es incorrecto")
    private String contactPhone;
    @Size(min = 6, max = 50, message = "El email debe tener entre 6 y 50 caracteres")
    @Pattern(regexp = "[^ ]*+", message = "Caracter: ' ' (Espacio en blanco) invalido")
    @Email(message = "Debe ser un email valido")
    private String contactEmail;

}
