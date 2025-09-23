package com.olivercp.unospring.service;

import com.olivercp.unospring.core.*;
import com.olivercp.unospring.core.model.cards.*;
import com.olivercp.unospring.core.model.players.*;

import com.olivercp.unospring.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final Game game;

    public GameService() {
        List<Player> players = List.of(
                new HumanPlayer("You"),
                new AiPlayer("Bot"));
        this.game = new Game(new GameContext(players));
    }

    public GameContextDTO getGameContext() {
        return new GameContextDTO(game.getContext());
    }

    public GameContextDTO playCard(int playerIndex, CardDTO cardDto) {
        Player player = game.getContext().getPlayers().get(playerIndex);
        if (player instanceof HumanPlayer human) {
            Card card = CardFactory.getCard(cardDto.getColor(), cardDto.getValue());
            human.setSelectedCard(card);
        }
        game.processTurn();
        return new GameContextDTO(game.getContext());
    }

    public GameContextDTO pickColor(int playerIndex, String color) {
        Player player = game.getContext().getPlayers().get(playerIndex);
        if (player instanceof HumanPlayer human) {
            human.setCardColor(CardColor.valueOf(color));
        }
        game.processTurn();
        return new GameContextDTO(game.getContext());
    }

    public GameContextDTO drawCard(int playerIndex) {
        Player player = game.getContext().getPlayers().get(playerIndex);
        if (player instanceof HumanPlayer human) {
            player.wantsToDrawACard(true);
        }
        game.processTurn();
        return new GameContextDTO(game.getContext());
    }

    public GameContextDTO passTurn(int playerIndex) {
        Player player = game.getContext().getPlayers().get(playerIndex);
        if (player instanceof HumanPlayer human) {
            game.endTurn();
        }
        return new GameContextDTO(game.getContext());
    }
}
