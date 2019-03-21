package feign;

import feign.gson.GsonEncoder;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 15:27
 * @Description:
 */
public class TestFeign {

    public static void main(String[] args) {
        LoginService loginService = Feign.builder().encoder(new GsonEncoder()).target(LoginService.class, "http://offline.openapi.zufangzi.com");
        String str = loginService.login("test.zufangzi.com", "4db4a895b2b325d8db9bb33a1ed5bbbb02eae53fd6345dd68c9ef18a87fc5f09", 1543565095714L, "BY00133", "111111");
        System.err.println(str);

    }
}
