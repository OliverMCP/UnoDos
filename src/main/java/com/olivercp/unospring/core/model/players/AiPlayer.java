package com.olivercp.unospring.core.model.players;

import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AiPlayer extends Player {

    public AiPlayer(String name) {
        this.name = name;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public Card chooseCard(Card topCard) {
        // Pick the first Card, that can be played
        for (Card card : hand) {
            if (topCard.isValid(card)) {
                System.out.println(name + " played: " + card.getId());
                return card;
            }
        }
        // If there is no card we have to draw
        wantsToDrawACard = true;
        System.out.println(name + " choose to draw a Card!");
        return null;
    }


    public CardColor chooseCardColor() {
        // Create a HashMap with CardColor as the key and a counter as value
        Map<CardColor, Integer> colors = new HashMap<>();

        CardColor bestColor = hand.getFirst().getColor();
        int colorCounter = 0; // number of cards with the same CardColor

        // Iterate trough every card in hand
        for (Card card : hand) {
            int count = colors.getOrDefault(card.getColor(), 0) + 1;
            colors.put(card.getColor(), count);

            // Then we check if the current HashMap value is bigger then the counter of the current Card.
            // If so, we update the values
            if (count > colorCounter) {
                colorCounter = colors.get(card.getColor());
                bestColor = card.getColor();
            }
        }
        return bestColor;
    }

}
