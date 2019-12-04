package com.aisino.mybatis;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:33
 * @Description:
 */
public class Configuration {

    final MapperRegistry mapperRegistry = new MapperRegistry();

    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type);
    }
}
