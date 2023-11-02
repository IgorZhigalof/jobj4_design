package ru.job4j.assertj;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisSphereIsWrong() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotSameAs("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotBlank()
                .isEqualTo("Cube");
    }

    @Test
    void whenGetNumberOfVertices() {
        int value = 8;
        Box box = new Box(value, 10);
        assertThat(value)
                .isGreaterThan(0)
                .isEqualTo(box.getNumberOfVertices());
    }

    @Test
    void whenGetNumberOfVerticesWithWrongInput() {
        Box box = new Box(1000, 10);
        int expected = -1;
        assertThat(box.getNumberOfVertices())
                .isNotSameAs(1000)
                .isEqualTo(expected);
    }

    @Test
    void whenIsExistIsTrue() {
        Box box = new Box(0, 10);
        assertThat(box.isExist())
                .isSameAs(true)
                .isTrue();
    }

    @Test
    void whenIsExistIsFalse() {
        Box box = new Box(-1, 10);
        assertThat(box.isExist())
                .isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void whenGetAreaAndIsExist() {
        Box box = new Box(0, 10);
        double expected = 1256.6368;
        assertThat(box.getArea())
                .isGreaterThan(1256)
                .isLessThan(1257)
                .isCloseTo(expected, Offset.offset(0.1));
    }

    @Test
    void whenGetAreaAndIsNotExist() {
        Box box = new Box(0, -1);
        double expected = 0;
        assertThat(box.getArea())
                .isLessThan(1)
                .isGreaterThan(-1)
                .isCloseTo(0.1, Offset.offset(0.9))
                .isEqualTo(expected);
    }
}