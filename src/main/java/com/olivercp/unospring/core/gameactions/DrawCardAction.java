package com.olivercp.unospring.core.gameactions;

import com.olivercp.unospring.core.Game;

public class DrawCardAction implements GameAction {
    private final int amount;

    public DrawCardAction(int amount) {
        this.amount = amount;
    }

    /// Called to execute a draw-penalty and reset the
    /// draw-penalty attributes of the GameContext-Class afterward
    @Override
    public void execute(Game game) {
        game.drawCard(amount);
        game.getContext().resetDrawPenalty();
    }
}
