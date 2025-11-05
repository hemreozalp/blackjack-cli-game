package com.hemreozalp.blackjack.infrastucture.cli;

import com.hemreozalp.blackjack.domain.service.InputService;

import java.util.Scanner;

public class ConsoleInputAdapter implements InputService {
    private final Scanner scanner = new Scanner(System.in);
    public String readCommand() {
        return scanner.nextLine();
    }
}
