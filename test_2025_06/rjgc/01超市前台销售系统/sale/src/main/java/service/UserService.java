package service;

import entity.User;
import java.util.List;

public interface UserService {

    /**
     * 用户登录
     */
    User login(String username, String password);

    /**
     * 根据用户名查找用户
     */
    User findByUsername(String username);

    /**
     * 根据ID查找用户
     */
    User findById(int id);

    /**
     * 查找所有用户
     */
    List<User> findAll();

    /**
     * 根据角色查找用户
     */
    List<User> findByRole(String role);

    /**
     * 添加用户
     */
    boolean addUser(User user);

    /**
     * 更新用户
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     */
    boolean deleteUser(int id);

    /**
     * 更新用户状态
     */
    boolean updateUserStatus(int id, int status);

    /**
     * 更新用户密码
     */
    boolean updatePassword(int id, String oldPassword, String newPassword);

    /**
     * 检查用户名是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 验证用户权限
     */
    boolean hasPermission(int userId, String permission);

    void register(User user); // 新增此方法声明

    List<User> listAll();
}