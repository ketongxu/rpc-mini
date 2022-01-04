import socket.ServerSocket;


/**
 * 服务消费者
 */
public class TestMain {


    public static void main(String[] args) {
        new ServerSocket().exposeService(8888,null);
    }
}
