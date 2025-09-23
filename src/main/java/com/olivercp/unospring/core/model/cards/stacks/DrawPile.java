package com.olivercp.unospring.core.model.cards.stacks;

import com.olivercp.unospring.core.GameContext;
import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;
import com.olivercp.unospring.core.model.cards.CardFactory;
import com.olivercp.unospring.core.model.cards.CardValue;
import com.olivercp.unospring.core.model.players.Player;
import com.olivercp.unospring.core.utils.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class DrawPile {
    private LinkedList<Card> drawPile;
    GameContext context;


    public DrawPile(GameContext context) {

        this.context = context;
        InitNewCardStack();
        RandomUtils.shuffleCardStack(drawPile);
        context.getDiscardPile().addCard(drawPile.removeFirst());

    }

    public void addCard(Card card) {
        if (card != null) {
            drawPile.addLast(card);
        }
    }


    /// Called when a player needs to draw a card
    ///
    /// @return the card the player draws
    public Card drawCard(Player player) {
        // Make sure the List isn't empty before drawing
        controlPile();
        Card drawnCard = drawPile.removeFirst();
        // Add card to player hand
        player.drawCard(drawnCard);
        return drawnCard;
    }

    /// Called when a player needs to draw multiple core.model.players.cards
    public void drawCards(Player player, int amount) {
        for (int i = 0; i < amount; i++) {
            this.drawCard(player);
        }
    }

    /// Called to check that the DrawPile is not empty
    private void controlPile() {
        // Get unused Cards from the DiscardPile, when the DrawPile is empty
        // Shuffle the Cards and add them to the end of the List
        if (drawPile.isEmpty()) {
            List<Card> discardPile = context.getDiscardPile().popDiscardPile();
            RandomUtils.shuffleCardStack(discardPile);
            addCards(discardPile);
        }
    }

    private void addCards(List<Card> cards) {
        drawPile.addAll(cards);
    }

    /// Called when a new game starts and a
    /// new set of core.model.players.cards needs to be initialized
    private void InitNewCardStack() {
        // Reset the DrawPile
        drawPile = new LinkedList<>();
        // For each color we need to create the core.model.players.cards
        for (CardColor color : CardColor.values()) {
            if (color == CardColor.WILD) continue; // Init Wild-Cards separate

            // We create alle the values
            for (CardValue value : CardValue.values()) {
                if (value == CardValue.WILD || value == CardValue.WILD_DRAW) continue; // Init Wild-Cards separate

                // Each value has an amount of core.model.players.cards to be generated
                for (int k = 0; k < value.getAmount(); k++) {
                    // CardFactory will return a Card-Object with the correct CardEffect
                    Card newCard = CardFactory.getCard(color, value);
                    // Add Card to the DrawPile
                    this.addCard(newCard);
                    // For debugging purposes
                    // System.out.println("Added: " + newCard.getColor() + " " + newCard.getValue());
                }
            }
        }

        // Wild-Cards separate (no color)
        for (int i = 0; i < CardValue.WILD.getAmount(); i++) {
            this.addCard(CardFactory.getCard(CardColor.WILD, CardValue.WILD));
        }
        for (int i = 0; i < CardValue.WILD_DRAW.getAmount(); i++) {
            this.addCard(CardFactory.getCard(CardColor.WILD, CardValue.WILD_DRAW));
        }

    }

}
