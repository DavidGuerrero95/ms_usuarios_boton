package co.com.udea.usecase;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.common.exceptions.ServiceException;
import co.com.udea.model.organizations.Organizations;
import co.com.udea.model.organizations.gateways.OrganizationsRepository;
import co.com.udea.model.util.Constants;
import co.com.udea.utils.ResponseMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class OrganizationsUseCase {
    private final OrganizationsRepository repository;

    public ResponseData save(Organizations organizations) {
        return Optional.ofNullable(organizations.getId())
                .flatMap(repository::findByIdOptional)
                .map(options -> edit(organizations))
                .orElseGet(() -> {
                    if (repository.existsByOrgName(organizations.getOrgName())) {
                        return ResponseMethods.getErrorResponseData(HttpStatus.BAD_REQUEST,
                                Constants.ORGANIZACION + organizations.getOrgName() +
                                        Constants.YA_EXISTE);
                    }
                    return ResponseMethods.getSuccessResponseData(repository.save(organizations),
                            HttpStatus.CREATED, Constants.ORGANIZACION_CREADA);
                });
    }

    public ResponseData edit(Organizations organizations) {
        try {
            Optional<Organizations> optionalSecurityGroups = Optional.ofNullable(organizations.getId())
                    .flatMap(repository::findByIdOptional);
            Organizations organizationsOptional = optionalSecurityGroups.orElseThrow(() ->
                    new ServiceException(Constants.EL_ID + organizations.getId() + Constants.NO_EXISTE));
            if (!organizations.getOrgName().equals(organizationsOptional.getOrgName())) {
                if (repository.existsByOrgName(organizations.getOrgName())) {
                    return ResponseMethods.getErrorResponseData(HttpStatus.BAD_REQUEST,
                            Constants.ORGANIZACION + organizations.getOrgName() + Constants.YA_EXISTE);
                }
                organizationsOptional.setOrgName(organizations.getOrgName());
            }
            organizationsOptional.setDescription(organizations.getDescription());
            return ResponseMethods.getSuccessResponseData(repository.save(organizationsOptional),
                    HttpStatus.OK, Constants.ORGANIZACION_MODIFICACION);
        } catch (ServiceException e) {
            return ResponseMethods.getErrorResponseData(e, Constants.ORGANIZACION_ERROR_EDICION);
        }
    }

    public ResponseData findAll() {
        return Optional.ofNullable(repository.findAll())
                .map(data -> ResponseMethods.getSuccessResponseData(data, HttpStatus.OK))
                .orElseGet(() -> ResponseMethods.getErrorResponseData(HttpStatus.BAD_REQUEST,
                        Constants.ORGANIZACION_ERROR_LISTAR));
    }

    public ResponseData getOrganizationsId(String id) {
        return repository.findByIdOptional(id)
                .map(userOptions -> ResponseMethods.getSuccessResponseData(userOptions, HttpStatus.OK))
                .orElseGet(() -> ResponseMethods.getErrorResponseData(HttpStatus.NOT_FOUND,
                        Constants.EL_ID + id + Constants.NO_EXISTE));
    }

    public ResponseData deleteOrganizationsId(String id) {
        return repository.findByIdOptional(id)
                .map(actualOption -> {
                    repository.deleteById(id);
                    return ResponseMethods.getSuccessResponseData(HttpStatus.OK, Constants.ORGANIZACION_ELIMINAR);
                })
                .orElseGet(() -> ResponseMethods.getErrorResponseData(HttpStatus.BAD_REQUEST, Constants.EL_ID
                        + id + Constants.NO_EXISTE));
    }
}
