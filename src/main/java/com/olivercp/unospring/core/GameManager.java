package com.olivercp.unospring.core;

import com.olivercp.unospring.core.model.cards.stacks.DrawPile;
import com.olivercp.unospring.core.model.players.Player;
import com.olivercp.unospring.core.utils.Logger;

import java.util.List;

/// GameManager: Singleton-Class. This class is responsible for setting up a new game.
public class GameManager {

    private static final GameManager INSTANCE = new GameManager();
    private GameContext context;
    private Game game;

    private GameManager() {
    }

    ///  Get the Singleton-Instance for GameManager
    public static GameManager getInstance() {
        return INSTANCE;
    }

    /// Called when a new game is started
    /**
     * Since GameManager is a Singleton and we need to know the Players
     * to initialize a new GameContext, Game and Renderer, we can't access
     * the constructor because it's private. Therefore, we need to call this
     * method to start a new Game. It's like the
     * @param players
     */
    public void startNewGame(List<Player> players) {
        context = new GameContext(players);
        game = new Game(context);
        assignDecksToPlayers(players);
        // The renderer depends on the GameManager to get the Context, Players, Cards, etc...
        // Therefore, we have to initialize the renderer after creating a game
    }

    /// Called in a loop to process all the GameActions in Queue
    public void updateGame() {
        game.processTurn();

    }

    /// Called when a new game starts, to initialize the starting hand of a player.
    /// Each player will be given seven core.model.players.cards
    private void assignDecksToPlayers(List<Player> players) {
        int AMOUNT_OF_CARDS = 7;
        DrawPile drawPile = context.getDrawPile();
        for (Player player : players) {
            drawPile.drawCards(player, AMOUNT_OF_CARDS);
        }
    }

    public GameContext getContext() {
        return context;
    }

    public Game getGame() {
        return game;
    }

    // TODO: pause, resume, reset, check for win
    public void pauseGame() {

    }

    public void resumeGame() {

    }

    public void resetGame() {
        //
        Logger.INSTANCE.printDebug("Game has been reset...");
    }
}
