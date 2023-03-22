package co.com.udea.model.users.gateways;

import co.com.udea.model.common.RequestPageable;
import co.com.udea.model.users.Users;

import java.util.HashMap;
import java.util.List;

public interface UsersRepository {
    Boolean existsById(String userId);
    HashMap findAll(RequestPageable requestPageable);
    Users findById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByCellPhone(String cellPhone);
    Users save(Users initialUser);
    void deleteById(String id);
}
