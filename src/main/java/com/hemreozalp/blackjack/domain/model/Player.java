package com.hemreozalp.blackjack.domain.model;

public class Player extends Participant {
    private final Wallet wallet;

    public Player(String name, Wallet wallet) {
        super(name);
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
