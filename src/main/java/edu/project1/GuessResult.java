package edu.project1;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {

    WordBoard wordBoard();

    int usedAttempts();

    int maxAttempts();

    @NotNull
    String message();

    default boolean isTerminal() {
        return this instanceof GuessResult.Win || this instanceof GuessResult.Defeat;
    }

    record Defeat(WordBoard wordBoard, int usedAttempts, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "Game over.";
        }
    }

    record Win(WordBoard wordBoard, int usedAttempts, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "You win!";
        }
    }

    record SuccessfulGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "Hit!";
        }
    }

    record FailedGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return String.format("Sorry, your guess was not found in the word. You used %d of your %d attempts.",
                usedAttempts(),
                maxAttempts());
        }
    }

    record AlreadyOpenedGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "You have already guessed that, please guess again.";
        }
    }

    record InvalidGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) implements GuessResult {
        @Override
        @NotNull
        public String message() {
            return "Your guess is not a lowercase english letter, please try again.";
        }
    }
}
