package co.com.udea.mongo.contacts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ContactsDataRepository
        extends MongoRepository<ContactsData, String>,
        QueryByExampleExecutor<ContactsData> {

    List<ContactsData> findByUserId(String userId);

    Boolean existsByUserIdAndCellPhone(String userId, String cellPhone);

}
