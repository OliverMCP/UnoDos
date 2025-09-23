package com.olivercp.unospring.core.model.players;

import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;

public class HumanPlayer extends Player {

    // Set by UI
    private Card selectedCard;
    private CardColor selectedCardColor;

    public HumanPlayer(String name) {
        super();
        this.name = name;
    }

    @Override
    public Card chooseCard(Card topCard) {
        Card chosenCard = selectedCard;
        clearSelectedCard();
        return chosenCard;
    }

    @Override
    public CardColor chooseCardColor() {
        return selectedCardColor;
    }

    public void setCardColor(CardColor color) {
        selectedCardColor = color;
    }


    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    private void clearSelectedCard() {
        this.selectedCard = null;
    }
}
