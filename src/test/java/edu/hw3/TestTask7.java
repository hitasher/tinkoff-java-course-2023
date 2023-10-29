package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask7 {
    @Test
    void getTreeMap_ShouldReturnTreeWithNullKey() {
        Map<String, String> tree = Task7.getTreeMapWithNullKey();
        assertThat(tree.containsKey(null)).isTrue();
    }
}
