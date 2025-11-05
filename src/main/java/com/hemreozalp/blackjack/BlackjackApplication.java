package com.hemreozalp.blackjack;

import com.hemreozalp.blackjack.domain.service.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackApplication implements CommandLineRunner {
    private final GameService gameService;

    public BlackjackApplication(GameService gameService) {
        this.gameService = gameService;
    }

    public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

    @Override
    public void run(String... args) {
        gameService.startNewGame();
    }
}
