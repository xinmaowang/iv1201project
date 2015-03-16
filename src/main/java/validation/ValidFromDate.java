package validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * The annotated target is checked to be a valid zip code.
 */
@Constraint(validatedBy = ValidFromDate.FromDateValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFromDate {

    String message() default "{validation.ValidFromDate.invalidFromDate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class FromDateValidator implements ConstraintValidator<ValidFromDate, Integer> {

        @Override
        public void initialize(ValidFromDate constraintAnnotation) {
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {

            Date d = new Date();
            
            Integer year = d.getYear() + 1900;
            
            return value >= year;

        }

    }
}
