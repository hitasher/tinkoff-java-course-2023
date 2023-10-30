package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask8 {
    @Test
    void backwardIterator_ShouldReturnElementsInReverseOrder() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(Arrays.asList(1, 2, 3));
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(3);
        assertThat(backwardIterator.next()).isEqualTo(2);
        assertThat(backwardIterator.next()).isEqualTo(1);
        assertThat(backwardIterator.hasNext()).isFalse();
        assertThatThrownBy(backwardIterator::next).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
