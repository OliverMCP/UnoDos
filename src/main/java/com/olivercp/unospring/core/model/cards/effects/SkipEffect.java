package com.olivercp.unospring.core.model.cards.effects;

import com.olivercp.unospring.core.GameContext;
import com.olivercp.unospring.core.gameactions.NextPlayerAction;

public class SkipEffect implements CardEffect {
    @Override
    public void apply(GameContext context) {
        context.getGameActionQueue().add(new NextPlayerAction());
    }
}
