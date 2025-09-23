package com.olivercp.unospring.core.model.cards;

import java.util.Locale;

public enum CardValue {
    ZERO(0, 1), ONE(1,2), TWO(2, 2), THREE(3, 2),
    FOUR(4, 2), FIVE(5,2), SIX(6,2), SEVEN(7,2),
    EIGHT(8,2), NINE(9,2), SKIP(10,2), REVERSE(10,2),
    DRAW(15,2), WILD(20,4), WILD_DRAW(25,4);

    private final int value;
    private final int amount;

    CardValue(int value, int amount) {
        this.value = value;
        this.amount = amount;
    }

    @Override
    public String toString() {
        if (this.value <= 9) {
            return Integer.toString(this.value);
        }
        String name = super.toString().toLowerCase(Locale.ROOT);
        String name2;
        if (name.contains("_")) {
            name2 = name.substring(name.indexOf("_") + 1);
            return name.substring(0, 1).toUpperCase() + name.substring(1, name.indexOf("_") + 1) + name2.substring(0,1).toUpperCase(Locale.ROOT) + name2.substring(1);
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public int getValue() {
        return value;
    }

    public int getAmount() {
        return amount;
    }
}
