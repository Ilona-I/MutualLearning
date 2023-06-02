package ua.nure.illiashenko.mutuallearning.annotation.mark;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = UniqueMarkValidator.class)
public @interface UniqueMarkValidation {

    String message() default "markWithThisTitleAlreadyExist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
