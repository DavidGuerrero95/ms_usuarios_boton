package co.com.udea.usecase;

import co.com.udea.model.common.*;
import co.com.udea.model.common.exceptions.ResponseException;
import co.com.udea.model.common.exceptions.ServiceException;
import co.com.udea.model.users.Users;
import co.com.udea.model.users.gateways.UsersRepository;
import co.com.udea.model.util.Constants;
import co.com.udea.requests.RequestRegister;
import co.com.udea.utils.DateMethods;
import co.com.udea.utils.ResponseMethods;
import co.com.udea.utils.UtilFunctions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class UsersUseCase {

    private final UsersRepository repository;

    public ResponseData list(RequestPageable requestPageable) {
        RequestPageable defaultRequestPageable = Stream.of(
                Optional.ofNullable(requestPageable.getPage()).orElse(0),
                Optional.ofNullable(requestPageable.getSize()).orElse(10),
                Optional.ofNullable(requestPageable.getOrder()).orElse(true),
                Optional.ofNullable(requestPageable.getFilter()).orElse("id")
        ).collect(Collectors.collectingAndThen(
                Collectors.toList(),
                list -> new RequestPageable((Integer) list.get(0), (Integer) list.get(1), (String) list.get(2),
                        (Boolean) list.get(3))
        ));
        return Optional.ofNullable(repository.findAll(defaultRequestPageable))
                .map(data -> ResponseMethods.getSuccessResponseData(data, HttpStatus.OK))
                .orElseGet(() -> ResponseMethods.getErrorResponseData(HttpStatus.INTERNAL_SERVER_ERROR, Constants.USUARIOS_ERROR_LISTAR));

    }

    public ResponseData edit(Users users) {
        ResponseData responseData = new ResponseData<>(null, HttpStatus.OK.value(), null, null);
        try {
            if (users.getId() != null) {
                if (repository.existsById(users.getId())) {
                    Users initialUser = repository.findById(users.getId());
                    if (users.getUsername() != null && !Objects.equals(initialUser.getUsername(), users.getUsername())) {
                        if (repository.existsByUsername(users.getUsername())) {
                            throw new ResponseException(HttpStatus.BAD_REQUEST, Constants.USERNAME_EXISTE);
                        }
                        initialUser.setUsername(users.getUsername());
                    }
                    if (users.getEmail() != null && !Objects.equals(initialUser.getEmail(), users.getEmail())) {
                        if (repository.existsByEmail(users.getEmail())) {
                            throw new ResponseException(HttpStatus.BAD_REQUEST, Constants.EMAIL_EXISTE);
                        }
                        initialUser.setEmail(users.getEmail());
                    }
                    if (users.getCellPhone() != null && !Objects.equals(initialUser.getCellPhone(),
                            users.getCellPhone())) {
                        if (repository.existsByCellPhone(users.getCellPhone())) {
                            throw new ResponseException(HttpStatus.BAD_REQUEST, Constants.CELULAR_EXISTE);
                        }
                        initialUser.setCellPhone(users.getCellPhone());
                    }
                    if (users.getFirstName() != null) initialUser.setFirstName(users.getFirstName());
                    if (users.getLastName() != null) initialUser.setLastName(users.getLastName());
                    if (users.getDocType() != null) initialUser.setDocType(users.getDocType());
                    if (users.getBirthDate() != null) {
                        if (DateMethods.verifyDate(users.getBirthDate().toString())) {
                            initialUser.setBirthDate(DateMethods.convert(users.getBirthDate().toString()));
                        } else {
                            throw new ResponseException(HttpStatus.BAD_REQUEST, Constants.FECHA_MAL_FORMATO);
                        }
                    }
                    if (users.getGender() != null) initialUser.setGender(users.getGender());
                    if (users.getAddress() != null) initialUser.setAddress(users.getAddress());
                    if (users.getGeoAddress() != null) initialUser.setGeoAddress(users.getGeoAddress());
                    if (users.getDepartment() != null) initialUser.setDepartment(users.getDepartment());
                    if (users.getCity() != null) initialUser.setCity(users.getCity());
                    if (users.getCountry() != null) initialUser.setCountry(users.getCountry());
                    if (users.getNeighborhood() != null) initialUser.setNeighborhood(users.getNeighborhood());
                    if (users.getZipCode() != null) initialUser.setZipCode(users.getZipCode());
                    if (users.getLandLine() != null) initialUser.setLandLine(users.getLandLine());
                    if (users.getEconomicActivity() != null)
                        initialUser.setEconomicActivity(users.getEconomicActivity());
                    if (users.getEconomicData() != null) initialUser.setEconomicData(users.getEconomicData());
                    if (users.getInterest() != null) initialUser.setInterest(users.getInterest());
                    if (users.getFamilyHead() != null) initialUser.setFamilyHead(users.getFamilyHead());
                    responseData.setData(repository.save(initialUser));
                    responseData.setMessage(Constants.EDICION_CORRECTAME);
                } else {
                    throw new ResponseException(HttpStatus.NOT_FOUND, Constants.USUARIOS_ID_NO_EXISTE);
                }
            } else {
                throw new ResponseException(HttpStatus.NO_CONTENT, Constants.USUARIOS_ID_NULL);
            }
        } catch (NonTransientDataAccessException | TransientDataAccessException e) {
            log.error(Constants.USUARIOS_ERROR_EDITAR + e.getMessage());
            throw new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.USUARIOS_ERROR_EDITAR);
        }
        return responseData;
    }

    public ResponseData view(String id) {
        return repository.findByIdOptional(id)
                .map(user -> ResponseMethods.getSuccessResponseData(user, HttpStatus.OK))
                .orElseGet(() -> ResponseMethods.getErrorResponseData(HttpStatus.NOT_FOUND,
                        Constants.EL_ID + id + Constants.NO_EXISTE));
    }

    public ResponseData delete(String id) {
        return repository.findByIdOptional(id)
                .map(actualOption -> {
                    repository.deleteById(id);
                    return ResponseMethods.getSuccessResponseData(HttpStatus.OK, Constants.USUARIO_ELIMINAR);
                })
                .orElseGet(() -> ResponseMethods.getErrorResponseData(HttpStatus.BAD_REQUEST, Constants.EL_ID
                        + id + Constants.NO_EXISTE));
    }

    public ResponseData createFirstUser() {
        ResponseData responseData = new ResponseData(null, HttpStatus.OK.value(), null, null);
        if (!repository.existsByUsername(Constants.ADMIN)) {
            Users users = new Users();
            users.setUsername("admin");
            users.setPassword(UtilFunctions.encode("Supp3rApPc1ty"));
            users.setEmail("coo.appcity@gmail.com");
            users.setCellPhone("");
            users.setRoles(Arrays.asList(RoleIds.ROLE_PLATFORM_ADMIN, RoleIds.ROLE_PLATFORM_DEVELOPER,
                    RoleIds.ROLE_CITIZEN));
            users.setAttempts(0);
            users.setActiveRole(new HashMap<>());
            users.setVerificationCode(0);
            users.setAccountEnabled(true);
            users.setFirstName("Admin");
            users.setLastName("UdeA");
            users.setDocType(DocType.DOCTYPE_IDENTITY_CARD);
            users.setBirthDate(LocalDateTime.of(1826, 01, 01, 1, 1));
            users.setGender(GenderType.NO_BINARY);
            users.setAddress("Cl. 67 #53-108");
            users.setGeoAddress(Arrays.asList("6.267309067688883","-75.56839701316706"));
            users.setDepartment(1);
            users.setCity(1);
            users.setCountry(1);
            users.setNeighborhood(1);
            users.setZipCode("050010");
            users.setLandLine("6042198332");
            users.setEconomicData(List.of(1));
            users.setInterest(List.of(1));
            users.setFamilyHead(false);
            users.setRegistrationDate(LocalDateTime.now());
            users.setFirstSeason(true);
            users.setColour(1);
            responseData.setData(repository.save(users));
            responseData.setMessage(Constants.USUARIO_CREADO);
        }
        throw new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ADMIN_YA_EXISTE);
    }

    public ResponseData register(RequestRegister requestRegister) {
        Users user = Users.builder()
                .username(requestRegister.getUsername())
                .password(UtilFunctions.encode(requestRegister.getPassword()))
                .email(requestRegister.getEmail())
                .cellPhone(requestRegister.getCellPhone())
                .roles(Collections.singletonList(RoleIds.ROLE_CITIZEN))
                .attempts(0)
                .activeRole(new HashMap() {{
                    put(AppIds.APP_PANIC_BUTTON, RoleIds.ROLE_CITIZEN);
                }})
                .verificationCode(0)
                .accountEnabled(true)
                .firstName(requestRegister.getFirstName())
                .lastName(requestRegister.getLastName())
                .docType(requestRegister.getDocType())
                .documentId(requestRegister.getDocumentId())
                .birthDate(requestRegister.getBirthDate())
                .gender(requestRegister.getGender())
                .address(requestRegister.getAddress())
                .geoAddress(requestRegister.getGeoAddress())
                .country(requestRegister.getCountry())
                .department(requestRegister.getDepartment())
                .city(requestRegister.getCity())
                .commune(requestRegister.getCommune())
                .neighborhood(requestRegister.getNeighborhood())
                .transportMeans(requestRegister.getTransportMeans())
                .zipCode("")
                .landLine("")
                .economicActivity(requestRegister.getEconomicActivity())
                .economicData(Collections.singletonList(1))
                .interest(Collections.singletonList(1))
                .familyHead(requestRegister.getFamilyHead())
                .registrationDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .firstSeason(true)
                .colour(requestRegister.getColour())
                .build();

        try {
            return ResponseMethods.getSuccessResponseData(repository.save(user), HttpStatus.CREATED);
        } catch (DataAccessException e){
            return ResponseMethods.getErrorResponseData(e, HttpStatus.BAD_REQUEST, Constants.USUARIO_CREADO_ERROR);
        }
    }
}
