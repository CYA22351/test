package dao;

import entity.User;
import java.util.List;

public interface UserDAO {

    User findByUsername(String username);


    User findById(int id);


    List<User> findAll();


    List<User> findByRole(String role);


    void save(User user);


    void update(User user);


    void delete(int id);


    boolean existsByUsername(String username);


    void updateStatus(int id, int status);


    void updatePassword(int id, String password);
}