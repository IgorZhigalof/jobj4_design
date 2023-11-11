package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenReplaceReplaces() {
        ListUtils.replaceIf(input, x -> x == 3, 5);
        assertThat(input).hasSize(2).contains(5);
    }

    @Test
    void whenReplaceDoesNotReplace() {
        ListUtils.replaceIf(input, x -> x == 11, 100);
        assertThat(input).hasSize(2).containsExactly(1, 3);
    }

    @Test
    void whenRemoveIfRemoves() {
        input.addAll(Arrays.asList(1, 3, 5, 6, 7, 8, 9, 10));
        ListUtils.removeIf(input, x -> x == 1);
        assertThat(input).hasSize(8).doesNotContainSequence(1);
    }

    @Test
    void whenRemoveIfDoesNotRemove() {
        ListUtils.removeIf(input, x -> x == 10);
        assertThat(input).containsExactly(1, 3);
    }

    @Test
    void whenRemoveAllRemoves() {
        ListUtils.removeAll(input, Arrays.asList(1, 3));
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveAllDoesNotRemove() {
        ListUtils.removeAll(input, Arrays.asList(9, 10, 11));
        assertThat(input).hasSize(2).containsExactly(1, 3);
    }
}