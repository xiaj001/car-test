package test;

/**
 * @author: xiajun003
 * @Date: 2018/12/2 21:51
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        TestService testService = new TestService();
        TestService login = testService.login(TestService.class);

        TestService login1 = TestService.getInstance().login(TestService.class);
    }
}
