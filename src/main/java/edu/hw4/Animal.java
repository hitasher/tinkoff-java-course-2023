package edu.hw4;

import lombok.Builder;

@Builder
public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        MALE, FEMALE
    }

    private static final int FISH_PAW_NUMBER = 0;
    private static final int BIRD_PAW_NUMBER = 2;
    private static final int CAT_AND_DOG_PAW_NUMBER = 4;
    private static final int SPIDER_PAW_NUMBER = 8;

    public int paws() {
        return switch (type) {
            case FISH -> FISH_PAW_NUMBER;
            case BIRD -> BIRD_PAW_NUMBER;
            case CAT, DOG -> CAT_AND_DOG_PAW_NUMBER;
            case SPIDER -> SPIDER_PAW_NUMBER;
        };
    }
}
