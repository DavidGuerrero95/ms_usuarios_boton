package co.com.udea.mongo.organizations;

import co.com.udea.model.common.RequestPageable;
import co.com.udea.model.organizations.Organizations;
import co.com.udea.model.organizations.gateways.OrganizationsRepository;
import co.com.udea.model.users.Users;
import co.com.udea.model.users.gateways.UsersRepository;
import co.com.udea.mongo.helper.AdapterOperations;
import co.com.udea.mongo.users.UsersData;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class OrganizationsDataAdapter
        extends AdapterOperations<Organizations, OrganizationsData, String, OrganizationsDataRepository>
        implements OrganizationsRepository {
    public OrganizationsDataAdapter(OrganizationsDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Organizations.class));
    }

    @Override
    public Optional<Organizations> findByIdOptional(String id) {
        return repository.findById(id).map(organizationsData -> {
            Organizations organizations = new Organizations();
            organizations.setId(organizationsData.getId());
            organizations.setOrgName(organizationsData.getOrgName());
            organizations.setType(organizationsData.getType());
            organizations.setDescription(organizationsData.getDescription());
            organizations.setAddress(organizationsData.getAddress());
            organizations.setGeoAddress(organizationsData.getGeoAddress());
            organizations.setContactName(organizationsData.getContactName());
            organizations.setContactPhone(organizationsData.getContactPhone());
            organizations.setContactEmail(organizationsData.getContactEmail());
            return organizations;
        });
    }

    @Override
    public boolean existsByOrgName(String orgName) {
        return repository.existsByOrgName(orgName);
    }
}
