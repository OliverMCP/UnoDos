package com.olivercp.unospring.core.gameactions;

import com.olivercp.unospring.core.Game;

public class IncreaseDrawPenaltyAction implements GameAction {
    int amount;

    /// Called by DrawFour/DrawTwoEffect with the specific amount
    public IncreaseDrawPenaltyAction(int amount) {
        this.amount = amount;
    }

    /// Called after a +4/+2 is played Start/continue a draw-penalty.
    /// The next Player has the chance to counter the draw-card by stacking a Card with
    /// a matching CardValue and give it to the next person/Player.
    /// If he can't counter, a DrawCardAction will be executed, and he
    /// has to draw the pending penalty.
    @Override
    public void execute(Game game) {
        game.getContext().setPendingDrawPenalty(true);
        game.getContext().increaseDrawCount(amount);
    }
}
