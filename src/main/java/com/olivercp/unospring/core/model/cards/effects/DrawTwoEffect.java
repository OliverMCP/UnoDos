package com.olivercp.unospring.core.model.cards.effects;

import com.olivercp.unospring.core.GameContext;
import com.olivercp.unospring.core.gameactions.IncreaseDrawPenaltyAction;

public class DrawTwoEffect implements CardEffect {
    @Override
    public void apply(GameContext context) {
        int DRAW_AMOUNT = 2;
        context.getGameActionQueue().add(new IncreaseDrawPenaltyAction(DRAW_AMOUNT));
    }
}
