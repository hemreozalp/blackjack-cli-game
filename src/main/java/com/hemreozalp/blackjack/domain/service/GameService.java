package com.hemreozalp.blackjack.domain.service;

import com.hemreozalp.blackjack.domain.model.Dealer;
import com.hemreozalp.blackjack.domain.model.Deck;
import com.hemreozalp.blackjack.domain.model.Player;
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
        Deck deck = new Deck();
        deckShuffler.shuffle(deck);

        Player player = new Player("You");
        Dealer dealer = new Dealer();

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

        evaluateWinner(player, dealer);
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

    private void evaluateWinner(Player player, Dealer dealer) {
        int playerScore = scoringStrategy.calculateScore(player.getHand());
        int dealerScore = scoringStrategy.calculateScore(dealer.getHand());

        outputService.printMessage("Final Scores:");
        outputService.printMessage(player.getName() + ": " + playerScore);
        outputService.printMessage(dealer.getName() + ": " + dealerScore);

        if (player.isBust() || (!dealer.isBust() && dealerScore > playerScore)) {
            outputService.printMessage("Dealer wins.");
        } else if (playerScore == dealerScore) {
            outputService.printMessage("Draw");
        } else {
            outputService.printMessage("You win!");
        }

    }
}
