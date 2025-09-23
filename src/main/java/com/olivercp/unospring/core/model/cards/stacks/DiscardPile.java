package com.olivercp.unospring.core.model.cards.stacks;

import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;

import java.util.LinkedList;
import java.util.List;

public class DiscardPile {
    private List<Card> discardPile;
    private Card currentCard;

    public DiscardPile() {
        discardPile = new LinkedList<>();
    }

    /// Called when a Card is played or the Game Starts
    public void addCard(Card card) {
        // if the Card is not valid do nothing and return
        if (!isCardValid(card)) {
            return;
        }
        // add the card from before to the discardPile
        // then set the played Card as current
        if (currentCard != null) {
            discardPile.add(currentCard);
        }
        currentCard = card;
    }

    ///  Called to compare core.model.players.cards or for rendering
    public Card getCurrentCard() {
        return currentCard;
    }

    /// Called when the DrawPile is empty so core.model.players
    /// can continue to draw core.model.players.cards.
    /// Cards will be deleted after the Call
    public List<Card> popDiscardPile() {
        // Returns a copy of unused core.model.players.cards (discardPile) and resets the List
        // Since the only used card is the currentCard Object (the card that was recently played)
        // We can simply return the whole List
        List<Card> unusedCards = discardPile;   // make a copy
        discardPile = new LinkedList<>();        // reset the discardPile
        return unusedCards;                     // return the copy
    }

    /// Check if a card can be played and added to the DiscardPile
    public boolean isCardValid(Card card) {
        // Returns true if we didn't set a currentCard yet
        if (currentCard == null) return true;
        // Or if the core.model.players.cards are matching in color or value
        if (card.getColor() == currentCard.getColor()) return true;
        if (card.getValue() == currentCard.getValue()) return true;
        // Or if the Card is a WildCard
        if (card.getColor() == CardColor.WILD) return true;

        // Otherwise card is not valid
        return false;
    }

}
