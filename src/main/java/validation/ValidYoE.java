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
 * Denna klass används för att validera antal år av erfarenhet. Detta får inte 
 * överstiga 50 år
 */
@Constraint(validatedBy = ValidYoE.YoEValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYoE {

    String message() default "{validation.ValidYoE.invalidYoE}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class YoEValidator implements ConstraintValidator<ValidYoE, Double> {

        @Override
        public void initialize(ValidYoE constraintAnnotation) {
        }

        @Override
        public boolean isValid(Double value, ConstraintValidatorContext context) {
            if(isEmpty(value, context)){
                return false;
            }
            
             return value < 50;

        }

        private boolean isEmpty(Double value, ConstraintValidatorContext context) {
            if (value == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{validation.ValidYoE.invalidYoE}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}
