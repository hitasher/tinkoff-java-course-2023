package edu.project1;

import org.jetbrains.annotations.NotNull;

public interface Dictionary {

    @NotNull
    String getRandomWord();

    int MINIMUM_WORD_LENGTH = 3;

    default void validateHiddenWord(String hiddenWord) {
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

    default boolean isNotValidLetter(char guess) {
        return guess < 'a' || guess > 'z';
    }
}
