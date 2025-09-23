package com.olivercp.unospring.dto;

import com.olivercp.unospring.core.GameContext;
import com.olivercp.unospring.core.model.players.AiPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class GameContextDTO {
    private List<PlayerDTO> players;
    private CardDTO topCard;
    private int currentPlayerIndex;
    private boolean gameOver;

    public GameContextDTO(GameContext context) {
        this.players = context.getPlayers().stream()
                .map(player -> new PlayerDTO(
                        player.getName(),
                        player.getHand().size(),
                        player instanceof AiPlayer
                )).toList();

        this.topCard = new CardDTO(
                context.getDiscardPile().getCurrentCard().getColor(),
                context.getDiscardPile().getCurrentCard().getValue()
        );

        this.currentPlayerIndex = context.getCurrentPlayerIndex();
        this.gameOver = context.isGameOver();
    }

    public List<PlayerDTO> getPlayers() { return players; }

    public CardDTO getTopCard() { return topCard; }

    public boolean isGameOver() { return gameOver; }
}
