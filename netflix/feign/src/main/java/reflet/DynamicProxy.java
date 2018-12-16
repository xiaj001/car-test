package reflet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 12:53
 * @Description:
 */
public class DynamicProxy implements InvocationHandler {


    public DynamicProxy(){

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("公共方法");
        return null;
    }


}
