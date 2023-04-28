package co.com.udea.model.common.annotations;
import java.lang.annotation.*;
import javax.validation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MapEnumValueValidator.class)
public @interface MapEnumValue {
    String message() default "El mapa contiene valores no válidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> keyClass();

    Class<? extends Enum<?>> valueClass();
}
