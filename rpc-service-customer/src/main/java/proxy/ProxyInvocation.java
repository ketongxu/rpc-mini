package proxy;

import model.RequestBean;
import model.UserInfo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProxyInvocation implements InvocationHandler {

    private  int port;

    private String url;

    public ProxyInvocation(int port,String url){
        this.port=port;
        this.url=url;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestBean rpcRequest = new RequestBean();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArg(args);
        //端口写死，可以自己做修改
        Socket socket = new Socket(url, port);
        //获取到输出流，把rpcRequest对象传到服务端，让服务端进行解析
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        os.writeObject(rpcRequest);
        //获取到输入流，从服务器端获取到执行的结果，并对其进行解析
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        UserInfo rpcResponse = (UserInfo) is.readObject();
        return rpcResponse;
    }
}
