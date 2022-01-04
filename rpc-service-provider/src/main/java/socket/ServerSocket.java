package socket;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerSocket {

    private static ExecutorService executorService = new ThreadPoolExecutor(5, 10, 3, TimeUnit.MINUTES, new ArrayBlockingQueue(10));


    public  static void exposeService(int port,Object instance){
        System.out.println("RPC服务器 已启动, 端口为：" + port);
        try {
            // TODO: 2021/12/30  可以加入nio，多路复用 
            java.net.ServerSocket socket=new java.net.ServerSocket(port);
            while (true){
                Socket socketAccept=socket.accept();
                executorService.execute(new HandleRequest(socketAccept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
