package exercise.connections;

import exercise.TcpConnection;

import java.util.List;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection tcp;

    public Disconnected(TcpConnection pTcp) {
        this.tcp = pTcp;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        tcp.setConnection(new Connected(this.tcp));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String message, List<String> pDataBuffer) {
        System.out.println("Error! Connection not established");
    }
}
// END
