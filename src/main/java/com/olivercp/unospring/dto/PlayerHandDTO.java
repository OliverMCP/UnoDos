package com.olivercp.unospring.dto;

import com.olivercp.unospring.core.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandDTO {
    private List<CardDTO> cards;

    public PlayerHandDTO(Player player) {
        this.cards = player.getHand().stream()
                .map(card -> new CardDTO(
                            card.getColor(),
                            card.getValue()
                )).toList();
    }

}
