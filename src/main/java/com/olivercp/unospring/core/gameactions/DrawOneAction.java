package com.olivercp.unospring.core.gameactions;

import com.olivercp.unospring.core.Game;

public class DrawOneAction implements GameAction {

    /// TODO: Set Skip-Button enabled & disable drawing
    ///
    @Override
    public void execute(Game game) {
        game.getContext().getDrawPile().drawCard(game.getContext().getCurrentPlayer());

    }
}
