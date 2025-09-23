package com.olivercp.unospring.core;

import com.olivercp.unospring.core.gameactions.DrawCardAction;
import com.olivercp.unospring.core.gameactions.NextPlayerAction;
import com.olivercp.unospring.core.gameactions.PickColorAction;
import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;
import com.olivercp.unospring.core.model.cards.CardValue;
import com.olivercp.unospring.core.model.cards.stacks.DiscardPile;
import com.olivercp.unospring.core.model.players.Player;

import java.util.List;

public class Game {
    private final GameContext context;

    public Game (GameContext context) {
        this.context = context;
    }

    public GameContext getContext() {
        return context;
    }

    /// Executes a core.model.players move
    public void processTurn() {

        Player currentPlayer = context.getCurrentPlayer();
        Card topCard = context.getDiscardPile().getCurrentCard();
        Card chosenCard = currentPlayer.chooseCard(topCard);

        if (handleWinCondition(currentPlayer)) return;
        if (handleGameOverCondition()) return;
        if (handleDrawPenalty(currentPlayer, topCard)) return;
        if (handleColorChange()) return;
        if (handleVoluntaryDraw(currentPlayer)) return;
        if (handleInvalidChoice(chosenCard, topCard))  return;

        playCard(currentPlayer, chosenCard);

        endTurn();
    }

    private boolean handleGameOverCondition() {
        List<Player> players = context.getPlayers();
        // Checks if there is only one player with Cards
        int amountOfPlayers = players.size();
        int endCondition = 1;
        // Amount of Players that has to win for a Game Over
        int gameOverCondition = amountOfPlayers - 1;
        int winnerCounter = 0;

        // Calculate the number of winners
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                winnerCounter++;
            }
        }
        // if there is only one player left ->
        // game over -> return true
        if (winnerCounter >= gameOverCondition) {
            context.setGameOver(true);
        }

        return winnerCounter >= gameOverCondition;
    }

    private boolean handleWinCondition(Player currentPlayer) {
        // Check if the Player has already won
        if (currentPlayer.hasWon()) {
            System.out.println(currentPlayer.getName() + " has won the game!");
            int index = context.getCurrentPlayerIndex();
            //context.removePlayer(currentPlayer);
            context.nextPlayer();
            return true;
        }
        return false;
    }

    private boolean handleDrawPenalty(Player currentPlayer, Card topCard) {
        // Check if a current draw penalty is active
        // (false if a +4/+2 wasn't played before)
        if (!context.isPendingDrawPenalty()) return false;
        // Check if the current player can't counter the +4/+2
        if (!currentPlayer.canCounterDrawPenalty(topCard) || currentPlayer.wantsToDrawACard()) {
            // the current draw penalty will be executed
            // a +2 will also skip to the next player
            int amount = context.getPendingDrawCount();
            context.getGameActionQueue().add(new DrawCardAction(amount));

            // +2 -> draw core.model.players.cards and go to the next Player
            if (topCard.getValue() == CardValue.DRAW) {
                context.getGameActionQueue().add(new NextPlayerAction());
                currentPlayer.wantsToDrawACard(false);
                context.resetDrawPenalty();
                endTurn();
                return true;
            }
            // +4 -> Player can play a card or draw another one
            if (topCard.getValue() == CardValue.WILD_DRAW) {
                currentPlayer.wantsToDrawACard(false);


                context.resetDrawPenalty();
                return false;
            }
        }
        return false;
    }


    private boolean handleColorChange() {
        if (!context.isPendingColorChange()) return false;
        CardColor selectedColor = context.getCurrentPlayer().chooseCardColor();
        if (selectedColor != null) {
            PickColorAction colorAction = new PickColorAction(selectedColor);
            return false;
        }
        return true;
    }

    private boolean handleVoluntaryDraw(Player currentPlayer) {
        if (!currentPlayer.wantsToDrawACard()) return false;

        // Draw a Card
        Card drawn = context.getDrawPile().drawCard(currentPlayer);
        Card topCard = context.getDiscardPile().getCurrentCard();
        if (drawn.isValid(topCard)) {
            playCard(currentPlayer, drawn);
        }
        currentPlayer.wantsToDrawACard(false);
        endTurn();
        return true;
    }

    private boolean handleInvalidChoice(Card chosenCard, Card topCard) {
        if (chosenCard == null) return true;             // Wait for Player
        return !topCard.isValid(chosenCard);             // returns true if Card is not valid
    }

    public void endTurn() {
        // After every Card is player go to the next Player
        context.getGameActionQueue().add(new NextPlayerAction());
        // Process all previous GameActions in Queue
        context.getGameActionQueue().processAll(this);
    }

    /**
     * Called by DrawCardAction if a player can't counter a +4/+2 or want's to draw a Card
     * @param amount amount of core.model.players.cards the current player has to draw
     */
    public void drawCard(int amount) {
        Player currentPlayer = context.getCurrentPlayer();
        context.getDrawPile().drawCards(currentPlayer, amount);
    }

    public void playCard(Player currentPlayer, Card chosenCard) {
        DiscardPile discardPile = context.getDiscardPile();
        // Add card to discardPile, apply CardEffect and update the current Player
        discardPile.addCard(chosenCard);
        // Remove Card from Players hand and add to DiscardPile
        currentPlayer.removeCard(chosenCard);
        // Apply the CardEffect (Will add Actions to the Queue)
        chosenCard.applyEffect(context);
    }

    public void nextPlayer() {
        context.nextPlayer();
    }

}
