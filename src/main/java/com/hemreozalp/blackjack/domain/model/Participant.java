package com.hemreozalp.blackjack.domain.model;

public abstract class Participant {
    protected final String name;
    protected final Hand hand;

    public Participant(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getHandValue() {
        return hand.calculateValue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public String toString() {
        return name + " -> " + hand.toString();
    }
}
