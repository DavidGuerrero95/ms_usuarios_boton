package co.com.udea.mongo.register;

import co.com.udea.mongo.users.UsersData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface RegisterDataRepository
        extends MongoRepository<RegisterData, String>,
        QueryByExampleExecutor<RegisterData> {

}
