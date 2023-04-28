package co.com.udea.model.register;

import co.com.udea.model.common.RoleIds;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Register {

    @JsonIgnore
    private String id;
    private String username;
    private String password;
    private String email;
    private String cellPhone;
    private String code;
    private Long minutes;
    private List<RoleIds> roles;

}
