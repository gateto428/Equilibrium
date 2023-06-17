package co.com.equilibrium.model.commons.exceptions;

import co.com.equilibrium.model.commons.enums.BusinessErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BussinessException extends  Exception{

    private final BusinessErrorMessage businessErrorMessage;
}
