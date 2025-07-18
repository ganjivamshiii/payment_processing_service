package com.hulkhiretech.payments.constant;

import lombok.Getter;

@Getter
public enum PaymentMethodEnum {
    APM(1, "APM");

    private final int id;
    private final String name;

    PaymentMethodEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PaymentMethodEnum fromId(int id) {
        for (PaymentMethodEnum e : values()) {
            if (e.id == id) return e;
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public static PaymentMethodEnum fromName(String name) {
        for (PaymentMethodEnum e : values()) {
            if (e.name.equalsIgnoreCase(name)) return e;
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
