package com.hemreozalp.blackjack.infrastucture.config;

import com.hemreozalp.blackjack.domain.service.GameService;
import com.hemreozalp.blackjack.domain.service.InputService;
import com.hemreozalp.blackjack.domain.service.OutputService;
import com.hemreozalp.blackjack.domain.strategy.BlackjackScoringStrategy;
import com.hemreozalp.blackjack.domain.strategy.DeckShuffler;
import com.hemreozalp.blackjack.domain.strategy.DefaultDeckShuffler;
import com.hemreozalp.blackjack.domain.strategy.ScoringStrategy;
import com.hemreozalp.blackjack.infrastucture.cli.ConsoleInputAdapter;
import com.hemreozalp.blackjack.infrastucture.cli.ConsoleOutputAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ScoringStrategy scoringStrategy() {
        return new BlackjackScoringStrategy();
    }

    @Bean
    public DeckShuffler deckShuffler() {
        return new DefaultDeckShuffler();
    }

    @Bean
    public InputService inputService() {
        return new ConsoleInputAdapter();
    }

    @Bean
    public OutputService outputService() {
        return new ConsoleOutputAdapter();
    }

    @Bean
    public GameService gameService(ScoringStrategy scoringStrategy,
                                   DeckShuffler deckShuffler,
                                   InputService inputService,
                                   OutputService outputService) {
        return new GameService(scoringStrategy, deckShuffler, inputService, outputService);
    }
}
