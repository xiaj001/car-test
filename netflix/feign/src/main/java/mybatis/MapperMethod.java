package mybatis;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:32
 * @Description:
 */
public class MapperMethod {

    public Object execute(Object[] args) {

        System.err.println("这是公共方法");
        return new User("123", "234");
    }
}
