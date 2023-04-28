package co.com.udea.mongo.users;

import co.com.udea.model.common.RequestPageable;
import co.com.udea.model.users.Users;
import co.com.udea.model.users.gateways.UsersRepository;
import co.com.udea.mongo.helper.AdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class UsersDataAdapter
        extends AdapterOperations<Users, UsersData, String, UsersDataRepository>
        implements UsersRepository {
    public UsersDataAdapter(UsersDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Users.class));

    }

    @Override
    public HashMap findAll(RequestPageable requestPageable) {
        Pageable pageable;
        if (requestPageable.getOrder()) {
            pageable = PageRequest.of(requestPageable.getPage(), requestPageable.getSize(),
                    Sort.by(requestPageable.getFilter()).ascending());
        } else {
            pageable = PageRequest.of(requestPageable.getPage(), requestPageable.getSize(),
                    Sort.by(requestPageable.getFilter()).descending());
        }
        Page<UsersData> all = repository.findAll(pageable);
        HashMap response = new HashMap();
        response.put("totalElements",all.getTotalElements());
        response.put("totaPages",all.getTotalPages());
        response.put("Users", repository.findAll(pageable).getContent().stream()
                .map(super::toEntity).collect(Collectors.toList()));
        return response;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByCellPhone(String cellPhone) {
        return repository.existsByCellPhone(cellPhone);
    }

    @Override
    public Optional<Users> findByIdOptional(String id) {

        return repository.findById(id).map(usersData -> {
            Users users = new Users();
            users.setId(usersData.getId());
            users.setUsername(usersData.getUsername());
            users.setPassword(usersData.getPassword());
            users.setEmail(usersData.getEmail());
            users.setCellPhone(usersData.getCellPhone());
            users.setRoles(usersData.getRoles());
            users.setAttempts(usersData.getAttempts());
            users.setActiveRole(usersData.getActiveRole());
            users.setVerificationCode(usersData.getVerificationCode());
            users.setAccountEnabled(usersData.getAccountEnabled());
            users.setFirstName(usersData.getFirstName());
            users.setLastName(usersData.getLastName());
            users.setDocType(usersData.getDocType());
            users.setDocumentId(usersData.getDocumentId());
            users.setBirthDate(usersData.getBirthDate());
            users.setGender(usersData.getGender());
            users.setAddress(usersData.getAddress());
            users.setGeoAddress(usersData.getGeoAddress());
            users.setDepartment(usersData.getDepartment());
            users.setCity(usersData.getCity());
            users.setCountry(usersData.getCountry());
            users.setNeighborhood(usersData.getNeighborhood());
            users.setZipCode(usersData.getZipCode());
            users.setLandLine(usersData.getLandLine());
            users.setEconomicActivity(usersData.getEconomicActivity());
            users.setEconomicData(usersData.getEconomicData());
            users.setInterest(usersData.getInterest());
            users.setFamilyHead(usersData.getFamilyHead());
            users.setRegistrationDate(usersData.getRegistrationDate());
            users.setFirstSeason(usersData.getFirstSeason());
            users.setColour(usersData.getColour());
            return users;
        });
    }
}
