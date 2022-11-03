package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class TcpConnection {
    private final String ip;
    private final int port;
    private Connection connection;
    private List<String> dataBuffer;

    public TcpConnection(String ip, int port) {
        this.dataBuffer = new ArrayList<>();
        this.ip = ip;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public String getCurrentState() {
        return this.connection.getCurrentState();
    }

    public void setConnection(Connection pConnection) {
        this.connection = pConnection;
    }

    public void connect() {
        this.connection.connect();
    }

    public void disconnect() {
        this.connection.disconnect();
    }

    public void write(String message) {
        this.connection.write(message, this.dataBuffer);
    }

    public void getDataBuffer() {
        System.out.println(this.dataBuffer);
    }
}
// END
