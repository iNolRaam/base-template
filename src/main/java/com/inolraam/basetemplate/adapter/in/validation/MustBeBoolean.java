
/**
 * Annotation for validating that a field is either {@code null} or a positive {@code Long} value greater than 0.
 * <p>
 * Can be applied to fields or methods. Used for entity IDs where {@code null} or positive values are allowed.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * @NullOrPositiveId
 * private Long id;
 * }
 * </pre>
 * </p>
 * <p>
 * Validation is performed by {@link com.inolraam.basetemplate.adapter.in.validation.impl.MustBeBooleanImpl}.
 * </p>
 *
 * @author iNolRaam
 * 
 */
package com.inolraam.basetemplate.adapter.in.validation;

import com.inolraam.basetemplate.adapter.in.validation.impl.MustBeBooleanImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = MustBeBooleanImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MustBeBoolean {

    /**
     * Default validation message.
     * @return the error message if validation fails
     */
    String message() default "Must be true or false";

    /**
     * Allows specification of validation groups.
     * @return the groups
     */
    Class<?>[] groups() default { };

    /**
     * Payload for clients of the Bean Validation API.
     * @return the payload
     */
    Class<? extends Payload>[] payload() default { };
}
