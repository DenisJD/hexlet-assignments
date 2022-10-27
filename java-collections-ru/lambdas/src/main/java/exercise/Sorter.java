package exercise;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return Stream.of(users)
                .flatMap(mapOfUser -> mapOfUser.stream()
                        .filter(elementOfMap -> elementOfMap.containsValue("male"))
                        .sorted(Comparator.comparing(elementOfMap -> LocalDate.parse(elementOfMap.get("birthday"))))
                        .map(elementOfMap -> elementOfMap.get("name")))
                .collect(Collectors.toList());
    }
}
// END
