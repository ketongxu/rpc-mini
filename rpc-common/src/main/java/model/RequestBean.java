package model;

import java.io.Serializable;

public class RequestBean implements Serializable {

    private String className;
    
    private  String methodName;

    private Object[] arg;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArg() {
        return arg;
    }

    public void setArg(Object[] arg) {
        this.arg = arg;
    }

    public RequestBean(String className, String methodName, Object[] arg){
        this.className=className;
        this.methodName=methodName;
        this.arg=arg;
    }
    public RequestBean(){

    }
}
