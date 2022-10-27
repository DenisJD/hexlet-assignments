package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String symbols, String word) {
        String lowSymbols = symbols.toLowerCase();
        String lowWord = word.toLowerCase();
        String[] arrayOfSymbols = lowSymbols.split("");
        String[] arrayOfWord = lowWord.split("");
        List<String> listOfSymbols = new ArrayList<>(Arrays.asList(arrayOfSymbols));
        for (String letter : arrayOfWord) {
            if (listOfSymbols.contains(letter)) {
                listOfSymbols.remove(letter);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
