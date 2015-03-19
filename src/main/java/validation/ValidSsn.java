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
 * Denna klass används för att validera personnummer. Personnummret måste bestå 
 * av endast siffror och är 10 tecken lång.
 */
@Constraint(validatedBy = ValidSsn.SsnValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSsn {

    String message() default "{validation.ValidSsn.invalidSsn}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SsnValidator implements ConstraintValidator<ValidSsn, String> {

        @Override
        public void initialize(ValidSsn constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(isEmpty(value, context)){
                return false;
            }
            
             try {
                Integer.parseInt(value);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return value.length() == 10;

        }

        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{validation.ValidSsn.noSsn}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}
