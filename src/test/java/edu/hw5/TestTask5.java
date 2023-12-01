package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask5 {
    @ParameterizedTest
    @ValueSource(strings = {
        "А123ВЕ777",
        "А123ВЕ77",
        "О777ОО177",
        "А001АА01",
        "А001АА100",
        "А001АА101",
        "К751ХВ164"
    })
    void isRegistrationPlateValid_ShouldReturnTrue(String plate) {
        assertThat(Task5.isRegistrationPlateValid(plate)).isTrue();
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777",
        "123ВЕ777",
        "А23ВЕ777",
        "А123ВЕ7",
        "А123В777",
        "А000ВЕ777",
        "А777ВЕ00",
        "А000ВЕ00",
        "А777ВЕ000",
        "А777ВЕ001"
    })
    void isRegistrationPlateValid_ShouldReturnFalse(String plate) {
        assertThat(Task5.isRegistrationPlateValid(plate)).isFalse();
    }
}
