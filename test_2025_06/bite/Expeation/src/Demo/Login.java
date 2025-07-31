package Demo;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/2 23:12
 * @description：
 * @modified By：
 * @version:
 */
public class Login {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void logInfo(String username,String password){
        if (!this.password.equals(password)){
            throw new PasswordException("密码错误异常");
        }
        if (!this.username.equals(username)){
            throw new UserException("用户名错误");

        }
        System.out.println("登陆成功！");
    }
}