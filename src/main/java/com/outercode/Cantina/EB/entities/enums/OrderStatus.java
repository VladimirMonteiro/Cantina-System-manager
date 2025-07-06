package com.outercode.Cantina.EB.entities.enums;

public enum OrderStatus {

    PAID("PAGO"),
    UNPAID("NÃO PAGO");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
