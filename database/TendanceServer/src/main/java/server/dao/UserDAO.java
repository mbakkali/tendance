package server.dao;

import server.User;

import java.util.List;

/**
 * Created by Patrik on 06/06/2016.
 */
public interface UserDAO {

    public void insert(User user);
    public User findByUserId(long user_id);
    public User findByUsername(String username);
    public List<User> findAll();
}
