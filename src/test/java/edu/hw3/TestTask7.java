package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask7 {
    @Test
    void getNullableTreeMap_ShouldReturnNullableTreeMap() {
        Map<String, String> tree = Task7.getNullableTreeMap();
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }
}
