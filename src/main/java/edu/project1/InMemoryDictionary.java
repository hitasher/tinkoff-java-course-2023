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
    public @NotNull String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(WORDS.length);
        return WORDS[randomIndex];
    }
}
