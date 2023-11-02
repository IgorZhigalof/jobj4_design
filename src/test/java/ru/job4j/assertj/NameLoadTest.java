package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenTryToParseEmptyContent() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenTryToParseContentWithNoEqual() {
        NameLoad nameLoad = new NameLoad();
        String content = "five is 5";
        assertThatThrownBy(() -> nameLoad.parse(content))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining(content);
    }

    @Test
    void whenContentStartsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        String content = "= is an equal";
        assertThatThrownBy(() -> nameLoad.parse(content))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(content);
    }

    @Test
    void whenContentEndsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        String content = "This is an =";
        assertThatThrownBy(() -> nameLoad.parse(content))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(content);
    }
}