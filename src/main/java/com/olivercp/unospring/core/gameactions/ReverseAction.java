package com.olivercp.unospring.core.gameactions;

import com.olivercp.unospring.core.Game;

public class ReverseAction implements GameAction {

    @Override
    public void execute(Game game) {
        // Will reverse the order of the core.model.players
        game.getContext().toggleReverseOrder();
    }
}
