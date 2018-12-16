package mybatis;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:32
 * @Description:
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    //真正的业务对象，也就是RealSubject对象
    private Class<T> mapperInterface;

    /**
     * 构造函数
     */
    public MapperProxy(Class<T> target) {
        this.mapperInterface = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        final MapperMethod mapperMethod = new MapperMethod();
        return mapperMethod.execute(args);
    }
}