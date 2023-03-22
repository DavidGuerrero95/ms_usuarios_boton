package co.com.udea.model.common.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<EnumValue, Object> {

    private Set<Object> enumValues;

    @Override
    public void initialize(EnumValue annotation) {
        Class<? extends Enum<?>> enumClass = annotation.enumClass();
        enumValues = Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return enumValues.contains(value.toString());
    }

}