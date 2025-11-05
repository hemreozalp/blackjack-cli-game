package com.hemreozalp.blackjack.domain.strategy;

import com.hemreozalp.blackjack.domain.model.Deck;

public interface DeckShuffler {
    void shuffle(Deck deck);
}
