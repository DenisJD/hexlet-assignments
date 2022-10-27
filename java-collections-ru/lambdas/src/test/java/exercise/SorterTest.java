package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

// BEGIN
class SorterTest {
    @Test
    void testTakeOldestMans1() {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        List<String> expected = List.of("John Smith", "Andrey Petrov", "Vladimir Nikolaev");
        List<String> result = Sorter.takeOldestMans(users);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testTakeOldestMans2() {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Petr I", "birthday", "1910-05-04", "gender", "male"),
                Map.of("name", "Elizabeth I", "birthday", "1920-05-04", "gender", "female"),
                Map.of("name", "Petr II", "birthday", "1930-05-04", "gender", "male"),
                Map.of("name", "Elizabeth II", "birthday", "1940-05-04", "gender", "female"),
                Map.of("name", "Petr III", "birthday", "1950-05-04", "gender", "male"),
                Map.of("name", "Elizabeth III", "birthday", "1960-05-04", "gender", "female")
        );
        List<String> expected = List.of("Petr I", "Petr II", "Petr III");
        List<String> result = Sorter.takeOldestMans(users);
        Assertions.assertEquals(expected, result);
    }
}
// END


