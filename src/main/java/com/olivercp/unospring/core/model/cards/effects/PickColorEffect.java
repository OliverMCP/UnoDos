package com.olivercp.unospring.core.model.cards.effects;

import com.olivercp.unospring.core.GameContext;

public class PickColorEffect implements CardEffect {

    /// TODO: Add ColorPicker-UI
    /// Called to change the color of the current Wild-Card
    @Override
    public void apply(GameContext context) {
        context.setPendingColorChange(true);

    }
}
