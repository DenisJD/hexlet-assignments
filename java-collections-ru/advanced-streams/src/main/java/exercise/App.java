package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String confFile) {
        return Stream.of(confFile.split("\n"))
                .filter(x -> x.contains("environment"))
                .flatMap(x -> Stream.of(x.split("\"")))
                .flatMap(x -> Stream.of(x.split(",")))
                .filter(x -> x.contains("X_FORWARDED_"))
                .map(x -> x.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
