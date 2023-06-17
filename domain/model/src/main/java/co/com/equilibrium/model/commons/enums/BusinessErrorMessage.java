package co.com.equilibrium.model.commons.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage {
    SERVICE_NOT_FOUNT("300", "Servicio no encontrado"),
    PERSON_NOT_FOUND_OR_INACTIVE("301", "Persona no encontrada o inactiva, " +
            "por favor revisa el celular o contacta al administrador"),
    INVALID_CREDENTIALS("302", "Credenciales inválidas, por favor revisa las credenciales"),
    PERSON_NOT_FOUND("303", "Persona no encontrada"),
    COURSE_NOT_FOUND("304", "Curso no encontrado"),
    PLAN_NOT_FOUND("305", "Plan no encontrado");
    private final String code;
    private final String message;
}
