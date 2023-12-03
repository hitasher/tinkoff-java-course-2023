package edu.hw8.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class QuotesStorage {
    private QuotesStorage() {
    }

    private final static Map<String, String> KEYWORD_TO_QUOTE = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    public static String getQuote(String keyword) {
        return KEYWORD_TO_QUOTE.getOrDefault(keyword, "Неизвестная цитата");
    }

    public static String getRandomKeyword() {
        Random random = ThreadLocalRandom.current();
        List<String> keys = new ArrayList<>(KEYWORD_TO_QUOTE.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

}
