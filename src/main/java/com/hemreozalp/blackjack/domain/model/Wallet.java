package com.hemreozalp.blackjack.domain.model;

public class Wallet {
    private int balance;

    public Wallet(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public void deposit(int amount) {
        if (amount > 0) balance += amount;
    }
}
