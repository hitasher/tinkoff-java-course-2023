package edu.hw8.task3;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractPasswordSearcher implements PasswordSearcher {

    protected static final int MIN_PASSWORD_LENGTH = 4;
    protected static final int MAX_PASSWORD_LENGTH = 6;
    protected static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    protected static final List<Character> ALPHABET_CHARACTERS =
        ALPHABET.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
}
