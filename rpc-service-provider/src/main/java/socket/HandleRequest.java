package socket;

import model.RequestBean;
import model.UserInfo;
import service.UserInfoServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class HandleRequest  implements  Runnable{

    private Socket socket;

    public HandleRequest(Socket socket){
       this.socket=socket;
    }
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RequestBean requestBean = (RequestBean) objectInputStream.readObject();
            //反射
            UserInfo userInfo = (UserInfo) this.deal(requestBean);
            //获取到输出流，把返回结果写出到客户端
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  static Object  deal(RequestBean requestBean)  {
        String className = requestBean.getClassName();
        //获取参数的类信息，用于指定方法获取
        Object[] params = requestBean.getArg();
        Class[] paramsType = null;
        if(params != null || params.length != 0){
            paramsType = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramsType[i] = params[i].getClass();
            }
        }
        try {
            Class<?> clazz = Class.forName(className);
            Method method1=clazz.getDeclaredMethod(requestBean.getMethodName(), paramsType);
            Method method = clazz.getMethod(requestBean.getMethodName(), paramsType);
            Object result = null;
            // TODO: 2021/12/30  需要修改扩展性 
            result = method.invoke(new UserInfoServiceImpl(), params);
            return  result;
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        return null;
    }
}
