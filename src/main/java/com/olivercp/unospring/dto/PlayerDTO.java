package com.olivercp.unospring.dto;

public class PlayerDTO {
    private String name;
    private int handSize;
    private boolean ai;

    public PlayerDTO(String name, int handSize, boolean ai) {
        this.name = name;
        this.handSize = handSize;
        this.ai = ai;
    }

    public String getName() { return name; }
    public int getHandSize() { return handSize; }
    public boolean isAi() { return ai; }
}
