package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import service.UserService;
import util.StringUtil;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String username, String password) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return null;
        }
        // 直接查数据库user表
        User user = userDAO.findByUsername(username);
        if (user != null && password.equals(user.getPassword()) && user.getStatus() == 1) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findByRole(String role) {
        return userDAO.findByRole(role);
    }

    @Override
    public boolean addUser(User user) {
        if (user == null || StringUtil.isEmpty(user.getUsername()) || StringUtil.isEmpty(user.getPassword())) {
            return false;
        }

        if (userDAO.existsByUsername(user.getUsername())) {
            return false;
        }

        try {
            userDAO.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null || user.getId() <= 0) {
            return false;
        }

        try {
            userDAO.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        if (id <= 0) {
            return false;
        }

        try {
            userDAO.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserStatus(int id, int status) {
        if (id <= 0) {
            return false;
        }

        try {
            userDAO.updateStatus(id, status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(int id, String oldPassword, String newPassword) {
        if (id <= 0 || StringUtil.isEmpty(oldPassword) || StringUtil.isEmpty(newPassword)) {
            return false;
        }

        User user = userDAO.findById(id);
        if (user == null || !oldPassword.equals(user.getPassword())) {
            return false;
        }

        try {
            userDAO.updatePassword(id, newPassword);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public boolean hasPermission(int userId, String permission) {
        User user = userDAO.findById(userId);
        if (user == null || user.getStatus() != 1) {
            return false;
        }

        // 系统管理员拥有所有权限
        if ("admin".equals(user.getRole())) {
            return true;
        }

        // 根据角色判断权限
        switch (permission) {
            case "product":
                return "admin".equals(user.getRole()) || "product".equals(user.getRole());
            case "cashier":
                return "admin".equals(user.getRole()) || "cashier".equals(user.getRole());
            case "member":
                return "admin".equals(user.getRole()) || "member".equals(user.getRole());
            case "report":
                return "admin".equals(user.getRole()) || "product".equals(user.getRole());
            default:
                return false;
        }
    }

    @Override
    public void register(User user) {

        userDAO.save(user);
    }

    @Override
    public List<User> listAll() {
        return Collections.emptyList();
    }
}
