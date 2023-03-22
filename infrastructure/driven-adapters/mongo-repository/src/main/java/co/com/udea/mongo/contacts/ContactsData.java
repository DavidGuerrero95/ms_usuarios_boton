package co.com.udea.mongo.contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "contacts")
@NoArgsConstructor
@AllArgsConstructor
public class ContactsData {

    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String cellPhone;

}
