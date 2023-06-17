package co.com.equilibrium.api.commons.handlers;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.BODY_MISSING_ERROR;
import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.HEADER_MISSING_ERROR;

@Component
@RequiredArgsConstructor
@Log
public class ValidatorHandler {
    private final Validator validator;

    private <T> void validateConstraints(Set<ConstraintViolation<T>> constraint){
        if(!constraint.isEmpty()){
            log.log(Level.WARNING, getMessage(constraint), BODY_MISSING_ERROR);
            throw new TechnicalException(getMessage(constraint), BODY_MISSING_ERROR);
        }
    }

    public <T> void validateObject(T object){
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        validateConstraints(violations);
    }

    public <T> void validateObject(T object, Class<?> clazz){
        Set<ConstraintViolation<T>> violations = validator.validate(object, clazz);
        validateConstraints(violations);
    }

    public <T> void validateObjectHeaders(T object){
        if(object != null){
            Set<ConstraintViolation<T>> violations = validator.validate(object);
            if(!violations.isEmpty()){
                throw new TechnicalException(getMessage(violations), HEADER_MISSING_ERROR);
            }
        }else{
            throw new TechnicalException(HEADER_MISSING_ERROR);
        }
    }

    private <T> String getMessage(Set<ConstraintViolation<T>> violations){
        return violations.stream()
                .map(c -> String.join(" ", c.getPropertyPath().toString(), c.getMessage()))
                .collect(Collectors.joining(", "));
    }
}
