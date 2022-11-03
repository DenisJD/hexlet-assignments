package exercise.connections;

import exercise.TcpConnection;

import java.util.List;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcp;

    public Connected(TcpConnection pTcp) {
        this.tcp = pTcp;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already established");
    }

    @Override
    public void disconnect() {
        tcp.setConnection(new Disconnected(this.tcp));
    }

    @Override
    public void write(String message, List<String> pDataBuffer) {
        pDataBuffer.add(message);
    }
}
// END
