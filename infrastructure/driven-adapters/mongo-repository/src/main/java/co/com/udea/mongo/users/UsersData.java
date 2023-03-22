package co.com.udea.mongo.users;

import co.com.udea.model.common.AppIds;
import co.com.udea.model.common.DocType;
import co.com.udea.model.common.GenderType;
import co.com.udea.model.common.RoleIds;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
@Document(collection = "COL_USER")
@NoArgsConstructor
@AllArgsConstructor
public class UsersData {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String cellPhone;
    private List<RoleIds> roles;
    private Integer attempts;
    private HashMap<AppIds, RoleIds> activeRole;
    private Integer verificationCode;
    private Boolean accountEnabled;
    private String firstName;
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
