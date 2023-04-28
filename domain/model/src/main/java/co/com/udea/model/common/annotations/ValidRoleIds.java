package co.com.udea.model.common.annotations;
import java.lang.annotation.*;
import javax.validation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidRoleIdsValidator.class)
public @interface ValidRoleIds {
    String message() default "Los valores de RoleIds no son válidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

