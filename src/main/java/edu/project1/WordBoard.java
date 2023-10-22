package edu.project1;

import java.util.Arrays;

public class WordBoard {
    private final static char HIDDEN_LETTER = '*';

    private final String hiddenWord;
    private char[] userWord;
    int numberOfOpenedLetters;

    public WordBoard(String hiddenWord) {
        this.hiddenWord = hiddenWord;
        initializeUserWord();
        numberOfOpenedLetters = 0;
    }

    public boolean isLetterOpened(char letter) {
        for (final char currentChar : userWord) {
            if (currentChar == letter) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotLetterInHiddenWord(char letter) {
        return hiddenWord.indexOf(letter) == -1;
    }

    public void openLetter(char letter) {
        if (isNotLetterInHiddenWord(letter)) {
            throw new IllegalArgumentException(String.format("Letter '%c' is not in hidden word", letter));
        }
        if (isLetterOpened(letter)) {
            throw new IllegalArgumentException(String.format("Letter '%c' is already opened", letter));
        }
        for (int i = 0; i < hiddenWord.length(); ++i) {
            if (letter == hiddenWord.charAt(i)) {
                ++numberOfOpenedLetters;
                userWord[i] = letter;
            }
        }
    }

    public boolean areAllLettersOpened() {
        return numberOfOpenedLetters == hiddenWord.length();
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void print() {
        System.out.print("The word: ");
        System.out.println(userWord);
    }

    private void initializeUserWord() {
        userWord = new char[hiddenWord.length()];
        Arrays.fill(userWord, HIDDEN_LETTER);
    }
}
