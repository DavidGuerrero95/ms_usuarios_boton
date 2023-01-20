package co.com.udea.model.contacts;

import lombok.*;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Contacts {

    private String id;
    private String userId;
    private String name;
    private String lastName;
    private String email;
    private String cellPhone;
}
