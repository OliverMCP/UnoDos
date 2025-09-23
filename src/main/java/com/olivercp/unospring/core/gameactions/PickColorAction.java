package com.olivercp.unospring.core.gameactions;

import com.olivercp.unospring.core.Game;
import com.olivercp.unospring.core.model.cards.Card;
import com.olivercp.unospring.core.model.cards.CardColor;

public class PickColorAction implements GameAction {
    CardColor cardColor;

    public PickColorAction(CardColor color) {
        cardColor = color;
    }

    @Override
    public void execute(Game game) {
        Card lastWildCard = game.getContext().getDiscardPile().getCurrentCard();
        lastWildCard.setColor(cardColor);
    }
}
