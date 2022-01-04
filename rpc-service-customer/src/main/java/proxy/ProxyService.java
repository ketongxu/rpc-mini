package proxy;

import java.lang.reflect.Proxy;

public class ProxyService {

    private  int port;

    private  String url;


    public  ProxyService(int port,String url){
        this.port=port;

        this.url=url;

    }


    public Object proxy(Class<?> clazz){

        return Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new ProxyInvocation(port,url));

    }
}
