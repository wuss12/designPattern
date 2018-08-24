package proxyPattern.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(object,args);
        after();
        return result;
    }
    public void before(){
        System.out.println("before buy house");
    }
    public void after(){
        System.out.println("after buy house");
    }
}
