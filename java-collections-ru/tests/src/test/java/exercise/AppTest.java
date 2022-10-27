package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AppTest {

    @Test
    void testTake() {
        int count = 0;
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        List<Integer> result = App.take(list, count);
        List<Integer> expected = List.of();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testTake1() {
        int count1 = 3;
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        List<Integer> result1 = App.take(list, count1);
        List<Integer> expected1 = List.of(1, 2, 3);
        Assertions.assertEquals(expected1, result1);
    }

    @Test
    void testTake2() {
        int count2 = 8;
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        List<Integer> result2 = App.take(list, count2);
        List<Integer> expected2 = List.of(1, 2, 3, 4, 5);
        Assertions.assertEquals(expected2, result2);
    }
}
