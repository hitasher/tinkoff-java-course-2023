package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {

    @Test
    public void hackerNewsTopStories_shouldReturnTopStories() {
        long[] topStories = Task5.hackerNewsTopStories();
        assertThat(topStories).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {37570037, 38331750})
    public void news_shouldReturnNonEmptyTitle(int id) {
        String actualTitle = Task5.news(id);
        assertThat(actualTitle).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void news_shouldReturnNull(int id) {
        String actualTitle = Task5.news(id);
        assertThat(actualTitle).isNull();
    }
}
