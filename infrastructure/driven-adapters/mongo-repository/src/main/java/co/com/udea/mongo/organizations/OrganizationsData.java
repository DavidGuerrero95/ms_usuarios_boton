package co.com.udea.mongo.organizations;

import co.com.udea.model.common.OrgType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "COL_ORGANIZATIONS")
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationsData {
    @Id
    private String id;
    private String orgName;
    private OrgType type;
    private String description;
    private String address;
    private List<String> geoAddress;
    private String contactName;
    private String contactPhone;
    private String contactEmail;

}
