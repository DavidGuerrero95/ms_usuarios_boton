package co.com.udea.model.organizations.gateways;

import co.com.udea.model.organizations.Organizations;

import java.util.List;
import java.util.Optional;

public interface OrganizationsRepository {
    Organizations save(Organizations organizations);

    Optional<Organizations> findByIdOptional(String s);

    boolean existsByOrgName(String orgName);

    List<Organizations> findAll();

    void deleteById(String id);
}
