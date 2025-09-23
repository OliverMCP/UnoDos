package com.olivercp.unospring.core;

import com.olivercp.unospring.core.model.cards.stacks.DiscardPile;
import com.olivercp.unospring.core.model.cards.stacks.DrawPile;
import com.olivercp.unospring.core.model.players.Player;

import java.util.List;

public class GameContext {
    private final List<Player> players;
    private final DrawPile drawPile;
    private final DiscardPile discardPile;
    private int currentPlayerIndex = 0;
    private boolean isReversed = false;
    private final GameActionQueue gameActions;
    private int pendingDrawCount = 0;
    private boolean pendingDrawPenalty = false;
    private boolean gameOver = false;
    private boolean pendingColorChange = false;

    public GameContext(List<Player> players) {
        this.players = players;
        discardPile = new DiscardPile();
        drawPile = new DrawPile(this);
        gameActions = new GameActionQueue();
    }

    /// Basic get/set-methods
    public boolean isPendingColorChange() {
        return pendingColorChange;
    }

    public void setPendingColorChange(boolean pendingColorChange) {
        this.pendingColorChange = pendingColorChange;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameActionQueue getGameActionQueue() {
        return gameActions;
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isReversed() {
        return isReversed;
    }

    /// Called after a Card with ReverseEffect is being played,
    /// This function will reverse the order of the core.model.players
    public void toggleReverseOrder() {
        isReversed = !isReversed;
    }

    /// Called to calculate the next Player after every Card is played,
    /// This function should only be called in the Game-/GameActionQueue-Class
    public void nextPlayer() {
        int step = isReversed ? -1 : 1;
        currentPlayerIndex = (currentPlayerIndex + step + getPlayerCount()) % getPlayerCount();
        /*
        if (isReversed) {
            currentPlayerIndex = (currentPlayerIndex - 1 + getPlayerCount()) % getPlayerCount();
            return;
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % getPlayerCount();
         */
    }


    public boolean isPendingDrawPenalty() {
        return pendingDrawPenalty;
    }

    /// Called by a GameAction to start a pending draw-penalty
    /// Example: When someone plays or stacks a +4/+2
    public void setPendingDrawPenalty(boolean pendingDrawPenalty) {
        this.pendingDrawPenalty = pendingDrawPenalty;
    }

    /// Called by GameActions to increase the pending draw penalty
    /// Example: A Player could counter a +4/+2 by stacking another +4/+2
    /// as long as the CardValue matches. If so, we increase the penalty and
    /// give it to the next person ;)
    public void increaseDrawCount(int amount) {
        pendingDrawCount = pendingDrawCount + amount;
    }

    /// After a draw-penalty is executed, this function
    /// will be called to reset the pending draw-penalty
    public void resetDrawPenalty() {
        setPendingDrawPenalty(false);
        pendingDrawCount = 0;
    }

    public int getPendingDrawCount() {
        return pendingDrawCount;
    }

}
