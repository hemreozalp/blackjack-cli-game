package com.hemreozalp.blackjack.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void deckShouldContain52CardsInitially() {
        Deck deck = new Deck();
        assertEquals(52, deck.remainingCards());
    }

    @Test
    void drawingCardShouldReduceDeckSize() {
        Deck deck = new Deck();
        deck.drawCard();
        assertEquals(51, deck.remainingCards());
    }

    @Test
    void drawingAllCardsShouldEmptyDeck() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) deck.drawCard();
        assertThrows(IllegalStateException.class, deck::drawCard);
    }
}
