package co.com.equilibrium.model.commons.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PayType {
    CASH("cash"),
    TRANSFER("transfer"),
    CARD("card");

    private final String value;
}
