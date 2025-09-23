package com.olivercp.unospring.core.model.cards;

import com.olivercp.unospring.core.model.cards.effects.*;

public class CardFactory {

    public static Card getCard(CardColor color, CardValue value) {
        CardEffect effect = switch (value) {
            case WILD -> new PickColorEffect();
            case WILD_DRAW -> new DrawFourEffect();
            case DRAW -> new DrawTwoEffect();
            case REVERSE -> new ReverseEffect();
            case SKIP -> new SkipEffect();
            default -> null;
        };
        return new Card(color, value, effect);
    }

}
