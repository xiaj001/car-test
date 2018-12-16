package mybatis;

import java.lang.reflect.Proxy;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:31
 * @Description:
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance() {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface);
        return newInstance(mapperProxy);
    }

    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }


}
