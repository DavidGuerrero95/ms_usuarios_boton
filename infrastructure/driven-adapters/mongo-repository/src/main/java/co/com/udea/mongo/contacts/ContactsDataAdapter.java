package co.com.udea.mongo.contacts;

import co.com.udea.model.contacts.Contacts;
import co.com.udea.model.contacts.gateways.ContactsRepository;
import co.com.udea.mongo.helper.AdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ContactsDataAdapter
        extends AdapterOperations<Contacts, ContactsData, String, ContactsDataRepository>
        implements ContactsRepository {
    public ContactsDataAdapter(ContactsDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Contacts.class));
    }

    @Override
    public List<Contacts> findByUserId(String userId) {
        List<ContactsData> byUser = repository.findByUserId(userId);
        return byUser.stream().map(super::toEntity).collect(Collectors.toList());
    }

    @Override
    public Contacts findByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone) {
        ContactsData byUser = repository.findByUserIdAndEmailAndCellPhone(userId,email, cellPhone);
        return toEntity(byUser);
    }

    @Override
    public Contacts findByUserIdAndEmail(String userId, String email) {
        ContactsData byUser = repository.findByUserIdAndEmail(userId, email);
        return toEntity(byUser);
    }

    @Override
    public Contacts findByUserIdAndCellPhone(String userId, String cellPhone) {
        ContactsData byUser = repository.findByUserIdAndCellPhone(userId,cellPhone);
        return toEntity(byUser);
    }

    @Override
    public Boolean existsByUserIdAndEmail(String userId, String email) {
        return repository.existsByUserIdAndEmail(userId, email);
    }

    @Override
    public Boolean existsByUserIdAndCellPhone(String userId, String cellPhone) {
        return repository.existsByUserIdAndCellPhone(userId, cellPhone);
    }

    @Override
    public Boolean existsByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone) {
        return repository.existsByUserIdAndEmailAndCellPhone(userId, email, cellPhone);
    }

    @Override
    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public void deleteByUserIdAndEmailAndCellPhone(String userId, String email, String cellPhone) {
        repository.deleteByUserIdAndEmailAndCellPhone(userId, email, cellPhone);
    }
}
