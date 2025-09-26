package com.olivercp.unospring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public CardColor getColor() {
        return color;
    }

    @JsonIgnore
    public CardValue getValue() {
        return value;
    }

    public String getCardId() {
        String id;
        if (this.getValue() == CardValue.WILD || this.getValue() == CardValue.WILD_DRAW) {
            id = this.getValue().toString();
            return id;
        }
        id = this.getColor().toString() + "_" + this.getValue().toString();
        return id;
    }
}
