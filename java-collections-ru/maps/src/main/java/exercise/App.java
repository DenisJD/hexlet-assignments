package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String words) {
        Map<String, Integer> result = new HashMap<>();
        String[] arrayOfWords = words.split(" ");
        if (words.length() > 0) {
            for (String word : arrayOfWords) {
                result.put(word, result.getOrDefault(word, 0) + 1);
            }
        }
        return result;
    }

    public static String toString(Map<String, Integer> wordsCount) {
        if (wordsCount.size() > 0) {
            StringBuilder result = new StringBuilder("{\n");
            for (String key : wordsCount.keySet()) {
                result.append("  ").append(key).append(": ").append(wordsCount.get(key)).append("\n");
            }
            return result + "}";
        } else {
            return "{}";
        }
    }
}
//END
