import socket.ServerSocket;

public class TestMain {


    public static void main(String[] args) {
        new ServerSocket().exposeService(8888,null);
    }
}
