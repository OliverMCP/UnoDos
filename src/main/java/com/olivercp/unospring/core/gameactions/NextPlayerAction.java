package com.olivercp.unospring.core.gameactions;

import com.olivercp.unospring.core.Game;

public class NextPlayerAction implements GameAction {

    @Override
    public void execute(Game game) {
        // Only Call once to skip because the Game-Class will also call nextPlayer()
        game.nextPlayer();
    }
}
