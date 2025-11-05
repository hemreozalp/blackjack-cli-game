package com.hemreozalp.blackjack.domain.strategy;

import com.hemreozalp.blackjack.domain.model.Hand;

public class BlackjackScoringStrategy implements ScoringStrategy {
    @Override
    public int calculateScore(Hand hand) {
        return hand.calculateValue();
    }
}
