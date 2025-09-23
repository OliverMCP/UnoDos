package com.olivercp.unospring.core.model.cards;

public enum CardColor {
    RED, GREEN, BLUE, YELLOW, WILD;

    @Override
    public String toString() {

        return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
    }
}
