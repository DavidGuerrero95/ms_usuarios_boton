package co.com.udea.mongo.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UsersDataRepository
        extends MongoRepository<UsersData, String>,
        QueryByExampleExecutor<UsersData> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByCellPhone(String cellPhone);
}
