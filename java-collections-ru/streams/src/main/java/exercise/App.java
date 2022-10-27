package exercise;

import java.util.List;

// BEGIN
class App {
    private static final List<String> FREE_DOMAINS = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static int getCountOfFreeEmails(List<String> emails) {
        return (int) emails.stream()
                .map(x -> x.split("@")[1])
                .filter(FREE_DOMAINS::contains)
                .count();
    }
}
// END
