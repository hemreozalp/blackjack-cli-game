package com.hemreozalp.blackjack.domain.strategy;

import com.hemreozalp.blackjack.domain.model.Deck;

public class DefaultDeckShuffler implements DeckShuffler {
    @Override
    public void shuffle(Deck deck) {
        deck.shuffle();
    }
}
