package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask4 {
    private static Stream<Arguments> integerToRoman_ShouldReturnRoman() {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(2, "II"),
            Arguments.of(3, "III"),
            Arguments.of(4, "IV"),
            Arguments.of(5, "V"),
            Arguments.of(6, "VI"),
            Arguments.of(7, "VII"),
            Arguments.of(8, "VIII"),
            Arguments.of(9, "IX"),
            Arguments.of(10, "X"),
            Arguments.of(50, "L"),
            Arguments.of(100, "C"),
            Arguments.of(500, "D"),
            Arguments.of(1000, "M"),
            Arguments.of(31, "XXXI"),
            Arguments.of(148, "CXLVIII"),
            Arguments.of(294, "CCXCIV"),
            Arguments.of(312, "CCCXII"),
            Arguments.of(421, "CDXXI"),
            Arguments.of(528, "DXXVIII"),
            Arguments.of(621, "DCXXI"),
            Arguments.of(782, "DCCLXXXII"),
            Arguments.of(870, "DCCCLXX"),
            Arguments.of(941, "CMXLI"),
            Arguments.of(1043, "MXLIII"),
            Arguments.of(1110, "MCX"),
            Arguments.of(1226, "MCCXXVI"),
            Arguments.of(1301, "MCCCI"),
            Arguments.of(1485, "MCDLXXXV"),
            Arguments.of(1509, "MDIX"),
            Arguments.of(1607, "MDCVII"),
            Arguments.of(1754, "MDCCLIV"),
            Arguments.of(1832, "MDCCCXXXII"),
            Arguments.of(1993, "MCMXCIII"),
            Arguments.of(2074, "MMLXXIV"),
            Arguments.of(2152, "MMCLII"),
            Arguments.of(2212, "MMCCXII"),
            Arguments.of(2343, "MMCCCXLIII"),
            Arguments.of(2499, "MMCDXCIX"),
            Arguments.of(2574, "MMDLXXIV"),
            Arguments.of(2646, "MMDCXLVI"),
            Arguments.of(2723, "MMDCCXXIII"),
            Arguments.of(2892, "MMDCCCXCII"),
            Arguments.of(2975, "MMCMLXXV"),
            Arguments.of(3051, "MMMLI"),
            Arguments.of(3185, "MMMCLXXXV"),
            Arguments.of(3250, "MMMCCL"),
            Arguments.of(3313, "MMMCCCXIII"),
            Arguments.of(3408, "MMMCDVIII"),
            Arguments.of(3501, "MMMDI"),
            Arguments.of(3610, "MMMDCX"),
            Arguments.of(3743, "MMMDCCXLIII"),
            Arguments.of(3844, "MMMDCCCXLIV"),
            Arguments.of(3888, "MMMDCCCLXXXVIII"),
            Arguments.of(3940, "MMMCMXL"),
            Arguments.of(3999, "MMMCMXCIX")
        );
    }

    @ParameterizedTest
    @MethodSource
    void integerToRoman_ShouldReturnRoman(int number, String expectedRoman) {
        // when
        String actualRoman = Task4.integerToRoman(number);
        // then
        assertThat(actualRoman).isEqualTo(expectedRoman);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99, 4000, 4001, 5000, 9999})
    void integerToRoman_ShouldThrowIllegalArgumentException(int number) {
        assertThatThrownBy(
            () -> Task4.integerToRoman(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
