package book.shop.anotation.validator;

import book.shop.anotation.FieldMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        String password = (String) new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        String repeatPassword = (String) new BeanWrapperImpl(value)
                .getPropertyValue(secondFieldName);
        return password == null && repeatPassword == null || password != null
                && password.equals(repeatPassword);
    }
}

