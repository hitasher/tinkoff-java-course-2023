package edu.project1;

import org.jetbrains.annotations.NotNull;

public class GameSession {

    private final static int MINIMUM_WORD_LENGTH = 3;

    private final WordBoard wordBoard;

    private final int maxAttempts;
    private int usedAttempts;

    public GameSession(String hiddenWord, int maxAttempts) {
        validateHiddenWord(hiddenWord);
        validateMaxAttempts(maxAttempts);
        this.wordBoard = new WordBoard(hiddenWord);
        this.maxAttempts = maxAttempts;
        usedAttempts = 0;
    }

    public @NotNull GuessResult guess(char guess) {
        if (isNotValidLetter(guess)) {
            return new GuessResult.InvalidGuess(wordBoard, usedAttempts, maxAttempts);
        }
        if (wordBoard.isLetterOpened(guess)) {
            return new GuessResult.AlreadyOpenedGuess(wordBoard, usedAttempts, maxAttempts);
        }
        return openGuess(guess);
    }

    public @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(wordBoard, usedAttempts, maxAttempts);
    }

    public void printWordBoard() {
        wordBoard.print();
    }

    private boolean isNotValidLetter(char guess) {
        return guess < 'a' || guess > 'z';
    }

    private @NotNull GuessResult openGuess(char guess) {
        if (wordBoard.isNotLetterInHiddenWord(guess)) {
            ++usedAttempts;
            if (usedAttempts == maxAttempts) {
                return new GuessResult.Defeat(wordBoard, usedAttempts, maxAttempts);
            }
            return new GuessResult.FailedGuess(wordBoard, usedAttempts, maxAttempts);
        }
        wordBoard.openLetter(guess);
        if (wordBoard.areAllLettersOpened()) {
            return new GuessResult.Win(wordBoard, usedAttempts, maxAttempts);
        }
        return new GuessResult.SuccessfulGuess(wordBoard, usedAttempts, maxAttempts);
    }

    private void validateMaxAttempts(int maxAttempts) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Number of max attempts must be positive");
        }
    }

    private void validateHiddenWord(String hiddenWord) {
        if (hiddenWord.length() < MINIMUM_WORD_LENGTH) {
            throw new IllegalArgumentException(String.format(
                "Given word %s is too short, word's length must be at least %d", hiddenWord, MINIMUM_WORD_LENGTH
            ));
        }
        for (int i = 0; i < hiddenWord.length(); ++i) {
            if (isNotValidLetter(hiddenWord.charAt(i))) {
                throw new IllegalArgumentException("All letters in hidden word must be lowercase english letters");
            }
        }
    }
}
