package mybatis;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 14:34
 * @Description:
 */
public class User {

    private String userName;
    private String passWord;

    public User(String userName,String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
