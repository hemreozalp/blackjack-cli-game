package com.hemreozalp.blackjack.domain.strategy;

import com.hemreozalp.blackjack.domain.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackScoringStrategyTest {

    @Test
    void shouldDelegateScoreCalculationToHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Rank.TEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));

        ScoringStrategy strategy = new BlackjackScoringStrategy();
        int score = strategy.calculateScore(hand);

        assertEquals(18, score);
    }
}
