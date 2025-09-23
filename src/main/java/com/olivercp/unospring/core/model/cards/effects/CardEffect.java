package com.olivercp.unospring.core.model.cards.effects;

import com.olivercp.unospring.core.GameContext;

public interface CardEffect {
    public void apply(GameContext context);
}
