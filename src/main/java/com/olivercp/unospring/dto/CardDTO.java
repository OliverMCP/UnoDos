package com.olivercp.unospring.dto;

import com.olivercp.unospring.core.model.cards.CardColor;
import com.olivercp.unospring.core.model.cards.CardValue;

public class CardDTO {
    private CardColor color;
    private CardValue value;

    public CardDTO(CardColor color, CardValue value) {
        this.color = color;
        this.value = value;
    }

    public CardDTO() {}

    public CardColor getColor() {
        return color;
    }

    public CardValue getValue() {
        return value;
    }
}
