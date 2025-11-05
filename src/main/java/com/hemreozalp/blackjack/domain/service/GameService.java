package com.hemreozalp.blackjack.domain.service;

import com.hemreozalp.blackjack.domain.model.Dealer;
import com.hemreozalp.blackjack.domain.model.Deck;
import com.hemreozalp.blackjack.domain.model.Player;
import com.hemreozalp.blackjack.domain.model.Wallet;
import com.hemreozalp.blackjack.domain.strategy.DeckShuffler;
import com.hemreozalp.blackjack.domain.strategy.ScoringStrategy;

public class GameService {
    private final ScoringStrategy scoringStrategy;
    private final DeckShuffler deckShuffler;
    private final InputService inputService;
    private final OutputService outputService;

    public GameService(ScoringStrategy scoringStrategy,
                       DeckShuffler deckShuffler,
                       InputService inputService,
                       OutputService outputService) {
        this.scoringStrategy = scoringStrategy;
        this.deckShuffler = deckShuffler;
        this.inputService = inputService;
        this.outputService = outputService;
    }

    public void startNewGame() {
        Wallet wallet = new Wallet(1000);
        Player player = new Player("You", wallet);
        Dealer dealer = new Dealer();

        boolean continuePlaying = true;

        while (continuePlaying && player.getWallet().getBalance() > 0) {
            Deck deck = new Deck();
            deckShuffler.shuffle(deck);

            outputService.printMessage("Your current balance: " + player.getWallet().getBalance());
            outputService.printMessage("Enter your bet amount: ");

            int bet;
            try {
                bet = Integer.parseInt(inputService.readCommand());
            } catch (NumberFormatException e) {
                outputService.printMessage("Invalid bet amount.");
                return;
            }

            if (!player.getWallet().withdraw(bet)) {
                outputService.printMessage("Insufficient balance.");
                continue;
            }

            // reseting hands for each round
            player.getHand().getCards().clear();
            dealer.getHand().getCards().clear();

            // First draw
            player.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());

            // First card is visible, second not
            outputService.printMessage("Dealer's visible card: " + dealer.getHand().getCards().get(0));
            outputService.printMessage(player.toString());

            playerTurn(player, deck);
            if (!player.isBust()) dealerTurn(dealer, deck);

            evaluateWinner(player, dealer, bet);

            if (player.getWallet().getBalance() <= 0) {
                outputService.printMessage("You're out of balance. Game over.");
                break;
            }

            boolean validAnswer = false;
            while (!validAnswer) {
                outputService.printMessage("Play again? (y/n): ");
                String again = inputService.readCommand().trim().toLowerCase();

                if (again.equals("y")) {
                    validAnswer = true;
                } else if (again.equals("n")) {
                    validAnswer = true;
                    continuePlaying = false;
                } else {
                    outputService.printMessage("Invalid command. Please enter 'y' or 'n'.");
                }
            }
        }

        outputService.printMessage("Final balance: " + player.getWallet().getBalance());
        outputService.printMessage("Thanks for playing!");
    }

    private void playerTurn(Player player, Deck deck) {
        while (true) {
            outputService.printMessage("Hit or Stand? (h/s): ");
            String command = inputService.readCommand().trim().toLowerCase();

            if (command.equals("h")) {
                player.addCard(deck.drawCard());
                outputService.printMessage(player.toString());

                if (player.isBust()) {
                    outputService.printMessage("You busted!");
                    break;
                }
            } else if (command.equals("s")) {
                break;
            } else {
                outputService.printMessage("Invalid command.");
            }
        }
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        outputService.printMessage("Dealer's turn...");
        while (scoringStrategy.calculateScore(dealer.getHand()) < 17) {
            dealer.addCard(deck.drawCard());
            outputService.printMessage(dealer.toString());
        }
        if (dealer.isBust()) outputService.printMessage("Dealer busted!");
    }

    private void evaluateWinner(Player player, Dealer dealer, int bet) {
        int playerScore = scoringStrategy.calculateScore(player.getHand());
        int dealerScore = scoringStrategy.calculateScore(dealer.getHand());

        outputService.printMessage("Final Scores:");
        outputService.printMessage(player.getName() + ": " + playerScore);
        outputService.printMessage(dealer.getName() + ": " + dealerScore);

        if (player.isBust() || (!dealer.isBust() && dealerScore > playerScore)) {
            outputService.printMessage("Dealer wins.");
        } else if (playerScore == dealerScore) {
            outputService.printMessage("Draw");
            player.getWallet().deposit(bet);
        } else {
            outputService.printMessage("You win!");
            player.getWallet().deposit(bet * 2);
        }

        outputService.printMessage("Your balance: " + player.getWallet().getBalance());
    }
}
