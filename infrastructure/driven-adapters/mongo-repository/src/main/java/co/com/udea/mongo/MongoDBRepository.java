package co.com.udea.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MongoDBRepository extends MongoRepository<Object/* change for adapter model */, String> , QueryByExampleExecutor<Object/* change for adapter model */> {
}
