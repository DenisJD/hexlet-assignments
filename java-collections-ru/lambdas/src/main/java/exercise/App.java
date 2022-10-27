package exercise;

import java.util.Arrays;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] arrayImage) {
        return Arrays.stream(arrayImage)
                .map(array -> Arrays.stream(array)
                        .flatMap(elementOfArray -> Arrays.stream(new String[]{elementOfArray, elementOfArray}))
                        .toArray(String[]::new))
                .flatMap(array -> Arrays.stream(new String[][]{array, array}))
                .toArray(String[][]::new);
    }
}
// END
