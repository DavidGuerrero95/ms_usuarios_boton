package co.com.udea.model.contacts.gateways;

import co.com.udea.model.contacts.Contacts;

import java.util.List;

public interface ContactsRepository {

    List<Contacts> findByUserId(String userId);

    Contacts findByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone);

    Contacts findByUserIdAndEmail(String userId, String email);

    Contacts findByUserIdAndCellPhone(String userId, String cellPhone);

    Boolean existsByUserIdAndEmail(String userId, String email);

    Boolean existsByUserIdAndCellPhone(String userId, String cellPhone);

    Boolean existsByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone);

    void deleteByUserId(String userId);

    void deleteByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone);

}
