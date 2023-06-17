package co.com.equilibrium.model.commons.exceptions;

import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import lombok.Getter;

@Getter
public class TechnicalException extends  RuntimeException {

    private final TechnicalExceptionEnum exception;

    public TechnicalException (Throwable error, TechnicalExceptionEnum technicalExceptionEnum){
        super(error);
        this.exception = technicalExceptionEnum;
    }

    public TechnicalException (String menssage, TechnicalExceptionEnum technicalExceptionEnum){
        super(menssage);
        this.exception = technicalExceptionEnum;
    }

    public TechnicalException (TechnicalExceptionEnum technicalExceptionEnum){
        super(technicalExceptionEnum.getMessage());
        this.exception = technicalExceptionEnum;
    }
}
