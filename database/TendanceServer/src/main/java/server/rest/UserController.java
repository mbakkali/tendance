package server.rest;

import org.springframework.web.bind.annotation.*;
import server.Outfit;
import server.User;
import server.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();
    private final int counter = 0; 

    @RequestMapping(method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam String username) {
        User resultuser = userDAO.getUserByUsername(username);
        return null;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id) throws SQLException{
        User result_user = userDAO.getUserByID(id);
        return result_user;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        userDAO.add_user(user);
        return user;

    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long id){
        User usertodelete = userDAO.getUserByID(id);
        userDAO.del_user(usertodelete);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable long id, @RequestBody User user){
        user.setUserId(id);
        user = userDAO.update_user(user);
        return user;
    }

    @RequestMapping(value = "/friends/{id}", method = RequestMethod.GET)
    public List<User> getFriends(@PathVariable long id){
        List<User> friends;
        return userDAO.getFriends(id);
    }

}