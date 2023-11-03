package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .anySatisfy(x ->
                        assertThat(x.length())
                                .isEqualTo(4))
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void whenToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> result = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(result)
                .allSatisfy(x ->
                        assertThat(x.length())
                                .isLessThan(10))
                .hasSize(5)
                .contains("first")
                .containsAnyOf("one", "1", "first")
                .last()
                .isEqualTo("fifth");
    }

    @Test
    void whenToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> result = simpleConvert.toSet("1", "2", "3", "4", "5");
        assertThat(result)
                .contains("1")
                .containsExactlyInAnyOrder("1", "2", "3", "4", "5")
                .doesNotContain("7");
    }

    @Test
    void whenToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> result = simpleConvert.toMap("0", "1", "2");
        assertThat(result)
                .containsEntry("0", 0)
                .containsKeys("0", "1", "2")
                .containsValue(2)
                .doesNotContainValue(3);
    }
}