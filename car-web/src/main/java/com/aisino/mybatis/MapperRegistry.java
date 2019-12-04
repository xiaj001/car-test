package com.aisino.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:31
 * @Description:
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type) {
        //模拟初始化knownMappers集合
        knownMappers.put(type, new MapperProxyFactory<T>(type));

        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return (T) mapperProxyFactory.newInstance();
    }

}
