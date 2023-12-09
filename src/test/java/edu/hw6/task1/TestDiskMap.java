package edu.hw6.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestDiskMap {

    @TempDir
    private Path tempDir;
    private DiskMap diskMap;

    @org.junit.jupiter.api.Nested public class nestedTestBlock {

        @BeforeEach
        void initEach() {
            diskMap = new DiskMap(tempDir.resolve("disk_map.txt"));
        }

        @Test
        void testSize() {
            assertThat(diskMap.size()).isEqualTo(0);
            diskMap.put("a", "b");
            assertThat(diskMap.size()).isEqualTo(1);
            diskMap.put("c", "d");
            assertThat(diskMap.size()).isEqualTo(2);
            diskMap.clear();
            assertThat(diskMap.size()).isEqualTo(0);
            diskMap.put("a", "b");
            diskMap.put("c", "d");
            diskMap.remove("a");
            diskMap.remove("z");
            assertThat(diskMap.size()).isEqualTo(1);
            diskMap.remove("c");
            assertThat(diskMap.size()).isEqualTo(0);
        }

        @Test
        void testIsEmpty() {
            assertThat(diskMap.isEmpty()).isTrue();
            diskMap.put("a", "b");
            assertThat(diskMap.isEmpty()).isFalse();
            diskMap.put("c", "d");
            assertThat(diskMap.isEmpty()).isFalse();
            diskMap.clear();
            assertThat(diskMap.isEmpty()).isTrue();
            diskMap.put("a", "b");
            diskMap.put("c", "d");
            diskMap.remove("a");
            diskMap.remove("z");
            assertThat(diskMap.isEmpty()).isFalse();
            diskMap.remove("c");
            assertThat(diskMap.isEmpty()).isTrue();
        }

        @Test
        void testContainsKey() {
            assertThat(diskMap.containsKey("a")).isFalse();
            diskMap.put("c", "d");
            assertThat(diskMap.containsKey("a")).isFalse();
            diskMap.put("a", "b");
            assertThat(diskMap.containsKey("a")).isTrue();
            diskMap.remove("a");
            assertThat(diskMap.containsKey("a")).isFalse();
        }

        @Test
        void testContainsValue() {
            assertThat(diskMap.containsValue("b")).isFalse();
            diskMap.put("c", "d");
            assertThat(diskMap.containsValue("b")).isFalse();
            diskMap.put("a", "b");
            assertThat(diskMap.containsValue("b")).isTrue();
            diskMap.remove("a");
            assertThat(diskMap.containsValue("b")).isFalse();
        }

        @Test
        void testGet() {
            assertThat(diskMap.get("a")).isNull();
            diskMap.put("c", "d");
            assertThat(diskMap.get("a")).isNull();
            diskMap.put("a", "b");
            assertThat(diskMap.get("a")).isEqualTo("b");
            diskMap.remove("a");
            assertThat(diskMap.get("a")).isNull();
        }

        @Test
        void testPut() {
            assertThat(diskMap.put("a", "b")).isNull();
            assertThat(diskMap.put("ab", "value_example")).isNull();
            assertThat(diskMap.get("ab")).isEqualTo("value_example");
            assertThat(diskMap.put("a", "c")).isEqualTo("b");
            assertThat(diskMap.get("a")).isEqualTo("c");
        }

        @Test
        void testRemove() {
            assertThat(diskMap.put("ab", "c")).isNull();
            assertThat(diskMap.put("abc", "value_example")).isNull();
            assertThat(diskMap.remove("a")).isNull();
            assertThat(diskMap.remove("ab")).isEqualTo("c");
            assertThat(diskMap.get("abc")).isEqualTo("value_example");
        }

        @Test
        void testPutAll() {
            Map<String, String> map = Map.of(
                "a", "b",
                "c", "d"
            );
            diskMap.putAll(map);
            assertThat(diskMap.entrySet()).isEqualTo(map.entrySet());
        }

        @Test
        void testClear() {
            diskMap.put("a", "b");
            diskMap.clear();
            assertThat(diskMap.isEmpty()).isTrue();
        }

        @Test
        void testKeySet() {
            assertThat(diskMap.keySet().isEmpty()).isTrue();
            diskMap.put("a", "b");
            diskMap.put("c", "d");
            assertThat(diskMap.keySet()).isEqualTo(Set.of("a", "c"));
            diskMap.remove("a");
            assertThat(diskMap.keySet()).isEqualTo(Set.of("c"));
            diskMap.put("c", "b");
            assertThat(diskMap.keySet()).isEqualTo(Set.of("c"));
        }

        @Test
        void testValues() {
            assertThat(diskMap.values().isEmpty()).isTrue();
            diskMap.put("a", "b");
            diskMap.put("c", "d");
            assertThat(diskMap.values()).isEqualTo(List.of("b", "d"));
            diskMap.remove("a");
            assertThat(diskMap.values()).isEqualTo(List.of("d"));
            diskMap.put("a", "d");
            assertThat(diskMap.values()).isEqualTo(List.of("d", "d"));
        }

        @Test
        void testEntrySet() {
            assertThat(diskMap.entrySet().isEmpty()).isTrue();
            diskMap.put("a", "b");
            diskMap.put("c", "d");
            assertThat(diskMap.entrySet()).isEqualTo(
                Set.of(
                    Map.entry("a", "b"),
                    Map.entry("c", "d")
                )
            );
            diskMap.remove("a");
            assertThat(diskMap.entrySet()).isEqualTo(
                Set.of(
                    Map.entry("c", "d")
                )
            );
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {".", "../."})
    void diskMapConstructor_ShouldThrowIllegalArgumentException(String filename) {
        assertThatThrownBy(
            () -> new DiskMap((tempDir.resolve(filename)))
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
