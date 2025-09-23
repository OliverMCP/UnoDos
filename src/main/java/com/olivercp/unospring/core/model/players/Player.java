package com.olivercp.unospring.core.model.players;

import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Player {
    protected LinkedList<Card> hand;
    protected String name;
    protected boolean wantsToDrawACard = false;

    public abstract Card chooseCard(Card topCard);
    public abstract CardColor chooseCardColor();

    public Player() {
        hand = new LinkedList<Card>();
    }

    public String getName() { return name; }

    public void drawCard(Card card) {
        hand.add(card);
        System.out.println("Player: " + name + " drew a Card!");
        System.out.println("Card ID: " + card.getId());
        System.out.println("These are your Cards now: ");
        for (Card c : hand) {
            int index = hand.indexOf(c) + 1;
            System.out.println("Card " + index + ": " + c.getId());

        }
    }

    public void drawCards(List<Card> cards) { hand.addAll(cards); }

    public void removeCard(Card card) { hand.remove(card); }

    public List<Card> getHand() { return hand; }

    /**
     * This function is called to check if a player want's to play a card
     * or decided to draw a card instead.
     * Players are not obligated to counter a +4/+2 if they can.
     * So they can decide to save their +4/+2 and take the current draw penalty
     * @return true if the player requested to draw a card
     */
    public boolean wantsToDrawACard() { return wantsToDrawACard; }

    /**
     * This setter-method will be called either by the enemy-AI or
     * if a real human Player clicked on the DrawPile to draw a card or take a penalty
     * @param value boolean wantsToDrawACard() will return value
     */
    public void wantsToDrawACard(boolean value) {
        wantsToDrawACard = value;
    }

    /**
     *
     * Called to check if a player has won
     * @return true if player has no Cards left
     */
    public boolean hasWon() {
        return hand.isEmpty();
    }

    /**
     * Called to check if a player has Uno (1 Card left)
     * @return true if player has no Cards left
     */
    public boolean hasUno() {
        return hand.size() == 1;
    }

    /**
     * Called to check if a player can counter a +4/+2
     * @param penaltyCard the last played +4/+2 (top card on the DiscardPile)
     * @return true if the player has a draw card of the same value
     */
    public boolean canCounterDrawPenalty(Card penaltyCard) {
        for (Card card : hand) {
            if (card.getValue() == penaltyCard.getValue()) {
                return true;
            }
        }
        return false;
    }

}
