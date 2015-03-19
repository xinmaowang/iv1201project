package validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * Denna klass används för att validera lösenord. Lösenorde måste vara minst
 * 6 tecken lång.
 */
@Constraint(validatedBy = ValidPassword.PasswordValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "{validation.ValidPassword.invalidPassword}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

        @Override
        public void initialize(ValidPassword constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(isEmpty(value, context)){
                return false;
            }
            
            
            return value.length() >= 6;

        }

        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{validation.ValidPassword.noPassword}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}
