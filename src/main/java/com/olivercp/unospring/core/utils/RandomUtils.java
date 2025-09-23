package com.olivercp.unospring.core.utils;

import com.olivercp.unospring.core.model.cards.Card;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    public static List<Card> shuffleCardStack(List<Card> cardStack) {
        Random rand = new Random();
        int amountOfRandomSwaps = rand.nextInt(cardStack.size(), cardStack.size() * 3);
        for (int i = 0; i < amountOfRandomSwaps; i++) {
            int index = rand.nextInt(cardStack.size());
            int secondIndex = rand.nextInt(cardStack.size());
            Card temp = cardStack.get(index);
            cardStack.set(index, cardStack.get(secondIndex));
            cardStack.set(secondIndex, temp);
        }
        return cardStack;
    }

}
