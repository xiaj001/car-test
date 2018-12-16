package mybatis;

import java.lang.reflect.Proxy;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:33
 * @Description:
 */
public class UserService {

    public static void main(String[] args) {
        /*final DefaultSqlSession sqlSession = new DefaultSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);*/

       //UserMapper userMapper = new MapperProxyFactory<UserMapper>(UserMapper.class).newInstance();

        final MapperProxy<UserMapper> mapperProxy = new MapperProxy<>(UserMapper.class);
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(UserMapper.class.getClassLoader(), new Class[]{UserMapper.class}, mapperProxy);

        User user = userMapper.selectUser();
        userMapper.selectUserName();
    }
}
