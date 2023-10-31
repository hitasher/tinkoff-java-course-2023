package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class InMemoryDictionary implements Dictionary {

    private final static String[] WORDS = {
        "lion",
        "bear",
        "fish",
        "eagle",
        "tiger",
        "giraffe",
        "butterfly",
        "jellyfish"
    };

    @Override
    @NotNull
    public String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(WORDS.length);
        String word = WORDS[randomIndex];
        this.validateHiddenWord(word);
        return word;
    }
}
