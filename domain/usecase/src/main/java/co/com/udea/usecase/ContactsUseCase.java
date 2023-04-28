package co.com.udea.usecase;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.common.exceptions.ResponseException;
import co.com.udea.model.contacts.Contacts;
import co.com.udea.model.contacts.gateways.ContactsRepository;
import co.com.udea.model.users.gateways.UsersRepository;
import co.com.udea.model.util.Constants;
import co.com.udea.utils.ResponseMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ContactsUseCase {

    private final ContactsRepository repository;
    private final UsersRepository usersRepository;

    public ResponseData save(Contacts contacts) {
        return Optional.ofNullable(contacts.getId())
                .flatMap(repository::findByIdOptional)
                .map(contactsEdit -> update(contactsEdit))
                .orElseGet(() -> {
                    if (contacts.getUserId() == null)
                        throw new ResponseException(HttpStatus.NOT_FOUND, Constants.CONTACTO_USER_ID_NULL);
                    else if (usersRepository.existsById(contacts.getUserId())) {
                        return ResponseMethods.getSuccessResponseData(repository.save(contacts),
                                HttpStatus.CREATED, Constants.CONTACTO_CREADO);
                    } else
                        throw new ResponseException(HttpStatus.NOT_FOUND, Constants.USUARIOS_ID_NO_EXISTE);
                });
    }

    /*public ResponseData update(Contacts contacts) {
        try {
            Optional<Contacts> optionalContacts = Optional.ofNullable(contacts.getId())
                    .flatMap(repository::findByIdOptional);
            Contacts contactsInitial = optionalContacts.orElseThrow(() ->  )
        }
    }*/

    public ResponseData update(Contacts contacts) {
        ResponseData responseData = new ResponseData(null, HttpStatus.OK.value(), null);
        try {
            if (repository.existsById(contacts.getId())) {
                Contacts initialContact = repository.findById(contacts.getId());
                if (contacts.getFirstName() != null) initialContact.setFirstName(contacts.getFirstName());
                if (contacts.getLastName() != null) initialContact.setFirstName(contacts.getFirstName());
                if (!Objects.equals(initialContact.getCellPhone(), contacts.getCellPhone())) {
                    initialContact.setCellPhone(contacts.getCellPhone());
                    if (repository.existsByUserIdAndCellPhone(contacts.getUserId(), contacts.getCellPhone())) {
                        throw new ResponseException(HttpStatus.BAD_REQUEST, "Ya existe el contacto");
                    }
                }
                if (!Objects.equals(initialContact.getEmail(), contacts.getEmail())) {
                    initialContact.setEmail(contacts.getEmail());
                    if (repository.existsByUserIdAndCellPhone(contacts.getUserId(), contacts.getCellPhone())) {
                        responseData.setError("null");
                        throw new ResponseException(HttpStatus.BAD_REQUEST, "Ya existe el contacto");
                    }
                }
                responseData.setData(repository.save(initialContact));
            } else {
                throw new ResponseException(HttpStatus.NO_CONTENT, "El contacto no existe");
            }
        } catch (NonTransientDataAccessException | TransientDataAccessException e) {
            log.error("Error al editar el contacto: " + e.getMessage());
            throw new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "error al editar el contacto");
        }
        return responseData;
    }

    public ResponseData delete(String id) {
        ResponseData responseData = new ResponseData(null, HttpStatus.BAD_REQUEST.value(), null);
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                responseData.setStatus(HttpStatus.OK.value());
                responseData.setMessage("Contacto eliminado correctamente");
            } else {
                throw new ResponseException(HttpStatus.NO_CONTENT, "El contacto no existe");
            }
        } catch (NonTransientDataAccessException | TransientDataAccessException e) {
            log.error("Error al eliminar el contacto: " + e.getMessage());
            throw new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "error al eliminar el contacto");
        }
        return responseData;
    }

    public ResponseData list(String userId) {
        ResponseData responseData = new ResponseData(null, HttpStatus.BAD_REQUEST.value(), null);
        try {
            if (usersRepository.existsById(userId)) {
                responseData.setData(repository.findByUserId(userId));
                responseData.setStatus(HttpStatus.OK.value());
            } else {
                throw new ResponseException(HttpStatus.NO_CONTENT, "UserId no existe.");
            }
        } catch (NonTransientDataAccessException | TransientDataAccessException e) {
            log.error("Error al listar los contactos: " + e.getMessage());
            throw new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "error al listar los contactos.");
        }
        return responseData;
    }

    public ResponseData view(String id) {
        ResponseData responseData = new ResponseData(null, HttpStatus.OK.value(), null);
        try {
            if (usersRepository.existsById(id)) {
                responseData.setData(repository.findById(id));
            } else {
                throw new ResponseException(HttpStatus.NO_CONTENT, "id no existe.");
            }
        } catch (NonTransientDataAccessException | TransientDataAccessException e) {
            log.error("Error al ver el contacto: " + e.getMessage());
            throw new ResponseException(HttpStatus.NO_CONTENT, "error al ver el contacto");
        }
        return responseData;
    }

}
