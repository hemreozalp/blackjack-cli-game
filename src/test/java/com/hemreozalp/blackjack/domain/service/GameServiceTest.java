package com.hemreozalp.blackjack.domain.service;

import com.hemreozalp.blackjack.domain.model.*;
import com.hemreozalp.blackjack.domain.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameServiceTest {

    private ScoringStrategy scoringStrategy;
    private DeckShuffler deckShuffler;
    private InputService inputService;
    private OutputService outputService;
    private GameService gameService;

    @BeforeEach
    void setup() {
        scoringStrategy = new BlackjackScoringStrategy();
        deckShuffler = Mockito.mock(DeckShuffler.class);
        inputService = Mockito.mock(InputService.class);
        outputService = Mockito.mock(OutputService.class);
        gameService = new GameService(scoringStrategy, deckShuffler, inputService, outputService);
    }

    @Test
    void shouldStartGameAndCallShufflerOnce() {
        when(inputService.readCommand()).thenAnswer(invocation -> "s");
        gameService.startNewGame();
        verify(deckShuffler, times(1)).shuffle(any(Deck.class));
    }

    @Test
    void shouldPrintMessagesDuringGame() {
        when(inputService.readCommand()).thenAnswer(invocation -> "s");
        gameService.startNewGame();
        verify(outputService, atLeastOnce()).printMessage(anyString());
    }
}
