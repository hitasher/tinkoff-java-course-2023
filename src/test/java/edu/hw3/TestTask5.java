package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.ContactParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask5 {
    public static Stream<Arguments> parseContacts_ShouldReturnSortedContacts() {
        return Stream.of(
            Arguments.of(
                Arrays.asList("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                "ASC",
                Arrays.asList(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                )
            ),
            Arguments.of(
                Arrays.asList("Paul Erdos", "Leonhard Euler", "Carl Gauss"),
                "DESC",
                Arrays.asList(
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")
                )
            ),
            Arguments.of(
                Arrays.asList("Miles", "Alistair", "Marcus"),
                "DESC",
                Arrays.asList(
                    new Contact("Miles"),
                    new Contact("Marcus"),
                    new Contact("Alistair")
                )
            ),
            Arguments.of(
                Arrays.asList("Ellie Flores", "Amanda Valencia", "Franklin", "Ellie"),
                "ASC",
                Arrays.asList(
                    new Contact("Ellie"),
                    new Contact("Franklin"),
                    new Contact("Ellie", "Flores"),
                    new Contact("Amanda", "Valencia")
                )
            ),
            Arguments.of(
                Arrays.asList("Robert", "Jenny Horn", "Issac Morrow", "Bruno", "Issac"),
                "DESC",
                Arrays.asList(
                    new Contact("Issac", "Morrow"),
                    new Contact("Jenny", "Horn"),
                    new Contact("Robert"),
                    new Contact("Issac"),
                    new Contact("Bruno")
                )
            ),
            Arguments.of(List.of(), "ASC", List.of()),
            Arguments.of(null, "DESC", List.of())
        );
    }

    @ParameterizedTest
    @MethodSource
    void parseContacts_ShouldReturnSortedContacts(
            List<String> originalNames, String order, List<Contact> expectedContacts
    ) {
        // when
        List<Contact> actualContacts = ContactParser.parseContacts(originalNames, order);
        // then
        assertThat(actualContacts).isEqualTo(expectedContacts);
    }

    @ParameterizedTest
    @NullSource
    void parseContacts_ShouldThrowNullPointerException(String order) {
        assertThatThrownBy(
            () -> ContactParser.parseContacts(List.of("Aarne"), order)
        ).isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> parseContacts_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(List.of("Jaime"), "A"),
            Arguments.of(List.of(""), "ASC"),
            Arguments.of(List.of(" "), "ASC"),
            Arguments.of(List.of(" Aarne"), "ASC"),
            Arguments.of(List.of("Aarne "), "ASC"),
            Arguments.of(List.of("aarne"), "ASC"),
            Arguments.of(List.of("AArne"), "ASC"),
            Arguments.of(List.of("Aarne AA"), "ASC"),
            Arguments.of(List.of("Aarne A A"), "ASC")
        );
    }

    @ParameterizedTest
    @MethodSource
    void parseContacts_ShouldThrowIllegalArgumentException(List<String> names, String order) {
        assertThatThrownBy(
            () -> ContactParser.parseContacts(names, order)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
