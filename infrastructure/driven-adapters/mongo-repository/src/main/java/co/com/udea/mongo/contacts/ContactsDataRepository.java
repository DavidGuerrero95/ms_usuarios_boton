package co.com.udea.mongo.contacts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ContactsDataRepository
        extends MongoRepository<ContactsData, String>,
        QueryByExampleExecutor<ContactsData> {

    List<ContactsData> findByUserId(String userId);

    ContactsData findByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone);

    ContactsData findByUserIdAndEmail(String userId,
                                      String email);

    ContactsData findByUserIdAndCellPhone(String userId, String cellPhone);

    Boolean existsByUserIdAndEmail(String userId, String email);

    Boolean existsByUserIdAndCellPhone(String userId, String cellPhone);

    Boolean existsByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone);

    void deleteByUserId(String userId);

    void deleteByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone);

}
