package co.com.udea.mongo.organizations;

import co.com.udea.mongo.users.UsersData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface OrganizationsDataRepository
        extends MongoRepository<OrganizationsData, String>,
        QueryByExampleExecutor<OrganizationsData> {

    boolean existsByOrgName(String orgName);
}
