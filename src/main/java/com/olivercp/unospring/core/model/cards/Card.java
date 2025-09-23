package com.olivercp.unospring.core.model.cards;

import com.olivercp.unospring.core.GameContext;
import com.olivercp.unospring.core.model.cards.effects.CardEffect;

import java.util.Objects;

public class Card {
    private CardColor color;                                    // Color of the Card
    private CardValue value;                                    // Value of the Card
    private CardEffect effect;                                  // No effect -> null

    public Card(CardColor color, CardValue value, CardEffect effect) {
        // Set Card Properties
        this.color = color;
        this.value = value;
        this.effect = effect;
    }

    public void applyEffect(GameContext context) {
        if (effect != null) {
            effect.apply(context);
        }
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public CardValue getValue() {
        return value;
    }

    public CardEffect getEffect() {
        return effect;
    }

    /// Called to check if a Card clicked by a player can be played on top of
    /// the Card the method is called from
    public boolean isValid(Card selectedCard) {
        if (value == selectedCard.getValue()) return true;
        if (color == selectedCard.getColor()) return true;
        if (selectedCard.color == CardColor.WILD) return true;
        return false;
    }

    public String getId() {
        String id;
        if (this.getValue() == CardValue.WILD || this.getValue() == CardValue.WILD_DRAW) {
            id = this.getValue().toString();
            return id;
        }
        id = this.getColor().toString() + "_" + this.getValue().toString();
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card other = (Card) obj;
        return this.color == other.color && this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }
}
