package co.com.udea.model.common.annotations;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
public class MapEnumValueValidator implements ConstraintValidator<MapEnumValue, Map<?, ?>> {

    private Class<? extends Enum<?>> keyEnumClass;
    private Class<? extends Enum<?>> valueEnumClass;

    @Override
    public void initialize(MapEnumValue annotation) {
        keyEnumClass = annotation.keyClass();
        valueEnumClass = annotation.valueClass();
    }

    @Override
    public boolean isValid(Map<?, ?> map, ConstraintValidatorContext context) {
        if (map == null) {
            return true;
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!keyEnumClass.isInstance(entry.getKey())) {
                return false;
            }
            if (!valueEnumClass.isInstance(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}