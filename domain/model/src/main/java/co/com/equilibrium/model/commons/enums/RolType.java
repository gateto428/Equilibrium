package co.com.equilibrium.model.commons.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RolType {
    COACH("coach"),
    ADMINISTRATOR("administrator"),
    CLIENT("client");

    private final String value;
}
