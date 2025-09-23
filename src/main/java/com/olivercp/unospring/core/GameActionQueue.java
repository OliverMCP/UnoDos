package com.olivercp.unospring.core;

import com.olivercp.unospring.core.gameactions.GameAction;

import java.util.LinkedList;
import java.util.Queue;

public class GameActionQueue {
    private final Queue<GameAction> actions = new LinkedList<>();

    public void add(GameAction action) {
        actions.add(action);
    }

    /// Called after every card played
    /// CardEffects will add GameActions to the Queue when played
    public void processAll(Game game) {
        while (!actions.isEmpty()) {
            actions.poll().execute(game);
        }
    }
}
