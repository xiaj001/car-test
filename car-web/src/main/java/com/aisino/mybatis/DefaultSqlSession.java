package com.aisino.mybatis;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:32
 * @Description:
 */
public class DefaultSqlSession {

    private Configuration configuration = new Configuration();

    public <T> T getMapper(Class<T> type) {
        return new MapperProxyFactory<T>(type).newInstance();
        //return configuration.getMapper(type);
    }
}
