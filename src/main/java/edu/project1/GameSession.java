package edu.project1;

import org.jetbrains.annotations.NotNull;

public class GameSession {

    private final WordBoard wordBoard;

    private final String hiddenWord;

    private final Dictionary dictionary;

    private final int maxAttempts;
    private int usedAttempts;

    public GameSession(Dictionary dictionary, int maxAttempts) {
        this.dictionary = dictionary;
        this.hiddenWord = dictionary.getRandomWord();
        validateMaxAttempts(maxAttempts);
        this.wordBoard = new WordBoard(hiddenWord);
        this.maxAttempts = maxAttempts;
        usedAttempts = 0;
    }

    public String getHiddenWord() {
        return this.hiddenWord;
    }

    @NotNull
    public GuessResult guess(char guess) {
        if (dictionary.isNotValidLetter(guess)) {
            return new GuessResult.InvalidGuess(wordBoard, usedAttempts, maxAttempts);
        }
        if (wordBoard.isLetterOpened(guess)) {
            return new GuessResult.AlreadyOpenedGuess(wordBoard, usedAttempts, maxAttempts);
        }
        return openGuess(guess);
    }

    @NotNull
    public GuessResult giveUp() {
        return new GuessResult.Defeat(wordBoard, usedAttempts, maxAttempts);
    }

    public void printWordBoard() {
        wordBoard.print();
    }

    @NotNull
    private GuessResult openGuess(char guess) {
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
}
