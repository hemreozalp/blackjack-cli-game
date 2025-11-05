package com.hemreozalp.blackjack.infrastucture.cli;

import com.hemreozalp.blackjack.domain.service.OutputService;

public class ConsoleOutputAdapter implements OutputService {
    public void printMessage(String message) {
        System.out.println(message);
    }
}
