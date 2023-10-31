package edu.project1;

import org.jetbrains.annotations.NotNull;

public class OneWordDictionary implements Dictionary {

    String word;

    public OneWordDictionary(String word) {
        validateHiddenWord(word);
        this.word = word;
    }

    @Override
    public @NotNull String getRandomWord() {
        return word;
    }
}
