package test;

/**
 * @author: xiajun003
 * @Date: 2018/12/2 21:50
 * @Description:
 */
public class TestService {

    public static TestService getInstance(){
        return new TestService();
    }

    public <T> T login(Class<T> tClass){
       return null;
    }
}
