package co.com.udea.model.organizationalunits;

import co.com.udea.model.common.AppIds;
import co.com.udea.model.common.OrgType;
import co.com.udea.model.common.RoleIds;
import co.com.udea.model.common.annotations.EnumValue;
import co.com.udea.model.common.annotations.MapEnumValue;
import co.com.udea.model.common.annotations.ValidRoleIds;
import co.com.udea.model.geographicalzones.GeographicalZones;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class OrganizationalUnits {
    private String id;
    private String orgId;
    private String unitName;
    @Size(max=200, message = "descripción no puede tener mas de 200 letras")
    private String description;
    private String adminUserId;
    @ValidRoleIds
    private List<HashMap<String, RoleIds>> personnel;
    private List<GeographicalZones> listGeoZoneId;
}
