package com.olivercp.unospring.controller;

import com.olivercp.unospring.dto.CardDTO;
import com.olivercp.unospring.dto.GameContextDTO;
import com.olivercp.unospring.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/game")
public class GameController {
    private final GameService gameService;

    // TODO:
    // - add "Uno"-Post methode when Player has only one card
    // - implement "Say Uno" in Game Logic -> handleUnoCondition

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/context")
    public GameContextDTO getContext() {
        return gameService.getGameContext();
    }

    @PostMapping("/play")
    public GameContextDTO playCard(@RequestParam int playerIndex, @RequestBody CardDTO cardDto) {
            return gameService.playCard(playerIndex, cardDto);
    }

    @PostMapping("/pickColor")
    public GameContextDTO pickColor(@RequestParam int playerIndex, @RequestBody Map<String, String> body) {
        return gameService.pickColor(playerIndex, body.get("color"));
    }

    @PostMapping("/passTurn")
    public GameContextDTO passTurn(@RequestParam int playerIndex) {
        return gameService.passTurn(playerIndex);
    }

}
