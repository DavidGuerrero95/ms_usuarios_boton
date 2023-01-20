package co.com.udea.usecase.contacts;

import co.com.udea.model.contacts.Contacts;
import co.com.udea.model.contacts.gateways.ContactsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;

@RequiredArgsConstructor
@Log
public class ContactsUseCase {

    private final ContactsRepository contactsRepository;

    public Boolean save(String userId, Contacts contacts) {
        if (contactsRepository.existsByUserIdAndCellPhone(userId, contacts.getCellPhone())) {
            String name = contacts.getName();
            String lastName = contacts.getLastName();
            String cellPhone = contacts.getCellPhone();
            contacts = contactsRepository.findByUserIdAndCellPhone(userId, contacts.getCellPhone());
            contacts.setName(name);
            contacts.setLastName(lastName);
            contacts.setCellPhone(cellPhone);
        } else if (contactsRepository.existsByUserIdAndEmail(userId, contacts.getEmail())) {
            String name = contacts.getName();
            String lastName = contacts.getLastName();
            String email = contacts.getEmail();
            contacts = contactsRepository.findByUserIdAndEmail(userId, contacts.getEmail());
            contacts.setName(name);
            contacts.setLastName(lastName);
            contacts.setEmail(email);
        } else contacts.setUserId(userId);
        try {
            contactsRepository.save(contacts);
            //guardarContactosUsuario(userId);
            return true;
        } catch (Exception  e) {
            log.severe("Error en la creación: " + e.getMessage());
            return false;
        }
    }

    public Boolean update(String userId, String email, String cellPhone, Contacts contacts) {
        Contacts c = contactsRepository.findByUserIdAndEmailAndCellPhone(userId, email, cellPhone);
        if (contacts.getName() != null) c.setName(contacts.getName());
        if (contacts.getLastName() != null) c.setLastName(contacts.getLastName());
        if (contacts.getEmail() != null) c.setEmail(contacts.getEmail());
        if (contacts.getCellPhone() != null) c.setCellPhone(contacts.getCellPhone());
        try {
            contactsRepository.save(c);
            //guardarContactosUsuario(userId);
            return true;
        } catch (Exception e) {
            log.severe("Error en la edición: " + e.getMessage());
            return false;
        }
    }

    public Boolean delete(String userId, String email, String cellPhone) {
        try {
            contactsRepository.deleteByUserIdAndEmailAndCellPhone(userId, email, cellPhone);
            //guardarContactosUsuario(userId);
            return true;
        } catch (Exception e) {
            log.severe("Error en eliminar contacto: " + e.getMessage());
            return false;
        }
    }


    public String getUserById(String username) {
        //return usersRepository.findByUsername(username).getId();
        return "ok";
    }

    public String getUsername(String userId) {
        /*AtomicReference<String> username = new AtomicReference<>("");
        Optional<Users> users = usersRepository.findById(userId);
        users.ifPresent(x -> {
            username.set(x.getUsername());
        });
        return username.get();*/
        return "ok";
    }

    public List<Contacts> listContacts(String userId) {
        return contactsRepository.findByUserId(userId);
    }

    private void guardarContactosUsuario(String userId) {
        /*String username = getUsername(userId);
        Users u = usersRepository.findByUsername(username);
        u.setContacts(contactsRepository.findByUserId(userId));
        usersRepository.save(u);*/
    }

}
