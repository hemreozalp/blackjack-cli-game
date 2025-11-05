package com.hemreozalp.blackjack.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    @Test
    void handCalculatesCorrectValueWithoutAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.EIGHT));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
        assertEquals(17, hand.calculateValue());
    }

    @Test
    void aceShouldCountAsElevenIfPossible() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.SIX));
        assertEquals(17, hand.calculateValue());
    }

    @Test
    void aceShouldCountAsOneIfOtherwiseBust() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(20, hand.calculateValue());
    }
}
