package com.hemreozalp.blackjack.domain.strategy;

import com.hemreozalp.blackjack.domain.model.Hand;

public interface ScoringStrategy {
    int calculateScore(Hand hand);
}
