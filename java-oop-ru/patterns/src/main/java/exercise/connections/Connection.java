package exercise.connections;

import java.util.List;

public interface Connection {
    // BEGIN
    String getCurrentState();
    void connect();
    void disconnect();
    void write(String message, List<String> data);
    // END
}
