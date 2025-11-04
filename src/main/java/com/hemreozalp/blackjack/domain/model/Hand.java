package com.hemreozalp.blackjack.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            value += card.getRank().getValue();
            if (card.getRank() == Rank.ACE) aceCount++;
        }

        return value;
    }

    public boolean isBust() {
        return calculateValue() > 21;
    }

    @Override
    public String toString() {
        return cards.toString() + " (Value: " + calculateValue() + ")";
    }
}
