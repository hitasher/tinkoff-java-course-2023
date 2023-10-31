package edu.project1;

import java.util.Scanner;

public class ConsoleHangman {

    private final static int MAXIMUM_NUMBER_OF_ATTEMPTS = 6;
    private final static String STOP_WORD = "stop";
    private final static Scanner SCANNER = new Scanner(System.in);

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void run() {
        Dictionary dictionary = new InMemoryDictionary();
        String hiddenWord = dictionary.getRandomWord();
        GameSession gameSession = new GameSession(hiddenWord, MAXIMUM_NUMBER_OF_ATTEMPTS);
        GuessResult guessResult = null;

        System.out.println("The hangman game starts, guess the word by entering single lowercase english letters.");
        System.out.printf("Enter '%s' if you want to give up.%n", STOP_WORD);

        while (guessResult == null || !guessResult.isTerminal()) {
            gameSession.printWordBoard();
            String input = inputLetterOrStopWord();
            if (input.equals(STOP_WORD)) {
                guessResult = gameSession.giveUp();
            } else {
                guessResult = gameSession.guess(input.charAt(0));
            }
            System.out.println(guessResult.message());
        }
        System.out.printf("The hidden word was '%s'.\n", hiddenWord);
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    private String inputLetterOrStopWord() {
        System.out.print("> ");
        String input = SCANNER.nextLine();
        while (!input.equals(STOP_WORD) && input.length() != 1) {
            System.out.println("Invalid input. Please enter a single letter or the stop word");
            input = SCANNER.nextLine();
        }
        return input;
    }
}
