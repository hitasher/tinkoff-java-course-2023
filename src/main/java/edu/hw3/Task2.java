package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static String NOT_BALANCED_BRACKET_SEQUENCE_EXCEPTION_MESSAGE =
        "Given brackets sequence is not balanced";

    private Task2() {
    }

    public static @NotNull List<String> clusterizeBrackets(String bracketSequence) {
        Objects.requireNonNull(bracketSequence);
        LOGGER.trace("Clusterizing brackets in {}", bracketSequence);
        final List<String> clusters = new ArrayList<>();
        int numberOfOpenedBrackets = 0;
        int nextClusterBeginIndex = 0;
        for (int i = 0; i < bracketSequence.length(); ++i) {
            char bracket = bracketSequence.charAt(i);
            if (bracket == '(') {
                ++numberOfOpenedBrackets;
            } else if (bracket == ')') {
                --numberOfOpenedBrackets;
            } else {
                throw new IllegalArgumentException("Given sequence must consist of only round brackets");
            }
            if (numberOfOpenedBrackets < 0) {
                throw new IllegalArgumentException(NOT_BALANCED_BRACKET_SEQUENCE_EXCEPTION_MESSAGE);
            } else if (numberOfOpenedBrackets == 0) {
                String cluster = bracketSequence.substring(nextClusterBeginIndex, i + 1);
                clusters.add(cluster);
                nextClusterBeginIndex = i + 1;
            }
        }
        if (numberOfOpenedBrackets != 0) {
            throw new IllegalArgumentException(NOT_BALANCED_BRACKET_SEQUENCE_EXCEPTION_MESSAGE);
        }
        return clusters;
    }
}
