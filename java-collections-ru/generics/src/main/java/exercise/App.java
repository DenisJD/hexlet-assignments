package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book : books) {
            int checkCount = 0;
            for (Map.Entry<String, String> bookCharacteristics : book.entrySet()) {
                for (Map.Entry<String, String> findCharacteristics : where.entrySet()) {
                    if (book.getOrDefault(bookCharacteristics.getKey(), "").equals(findCharacteristics.getValue())) {
                        checkCount++;
                    }
                }
            }
            if (checkCount == where.size()) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
