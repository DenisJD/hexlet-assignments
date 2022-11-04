package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    private String message;
    NegativeRadiusException(String pMessage) {
        this.message = pMessage;
    }
}
// END
