package reflet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 12:54
 * @Description:
 */
public class Test {

    public static <T> T getInstance(T login1){
        InvocationHandler handler = new DynamicProxy();
        T login = (T)Proxy.newProxyInstance(handler.getClass().getClassLoader(), login1
                .getClass().getInterfaces(), handler);
        return login;
    }
    public static void main(String[] args) {
        Login login1 = new LoginImpl();
     /*   InvocationHandler handler = new DynamicProxy();
        Login instance = (Login)Proxy.newProxyInstance(login1.getClass().getClassLoader(), login1
                .getClass().getInterfaces(), handler);
        instance.isLogin();*/

        Login instance1 = getInstance(login1);
        instance1.isLogin();
        System.err.println("over");
    }
}
