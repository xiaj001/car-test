package feign;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 15:24
 * @Description:
 */
public interface LoginService {

    @RequestLine("POST /user/login")
    String login(@Param("appId")String appId,@Param("signCode")String signCode,@Param("mt")Long mt,@Param("userName")String userName,@Param("password")String password);

    @RequestLine("POST user/verifyToken")
    String verifyToken();
}
