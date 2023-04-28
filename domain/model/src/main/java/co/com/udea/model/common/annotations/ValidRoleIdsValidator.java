package co.com.udea.model.common.annotations;

import co.com.udea.model.common.RoleIds;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.List;

public class ValidRoleIdsValidator implements ConstraintValidator<ValidRoleIds, List<HashMap<String, RoleIds>>> {

    @Override
    public boolean isValid(List<HashMap<String, RoleIds>> personnel, ConstraintValidatorContext context) {
        if (personnel == null) {
            return true;
        }
        for (HashMap<String, RoleIds> map : personnel) {
            for (RoleIds role : map.values()) {
                if (role == null) {
                    return false;
                }
            }
        }
        return true;
    }

}
