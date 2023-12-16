package edu.project4;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestListUtils {
    @Test
    public void random_ShouldReturnElementInList() {
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Integer picked = ListUtils.random(list);
        assertThat(list).contains(picked);
    }
}
