package com.olivercp.unospring.core.model.cards.effects;

import com.olivercp.unospring.core.GameContext;
import com.olivercp.unospring.core.gameactions.IncreaseDrawPenaltyAction;

public class DrawFourEffect implements CardEffect {

    @Override
    public void apply(GameContext context) {
        int DRAW_AMOUNT = 4;
        context.getGameActionQueue().add(new IncreaseDrawPenaltyAction(DRAW_AMOUNT));
        context.setPendingColorChange(true);
    }
}
