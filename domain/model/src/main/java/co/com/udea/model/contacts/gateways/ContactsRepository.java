package co.com.udea.model.contacts.gateways;

import co.com.udea.model.contacts.Contacts;

import java.util.List;

public interface ContactsRepository {

    List<Contacts> findByUserId(String userId);

    Boolean existsByUserIdAndCellPhone(String userId, String cellPhone);

    Contacts save(Contacts contacts);

    Boolean existsById(String id);

    Contacts findById(String id);

    void deleteById(String id);
}
