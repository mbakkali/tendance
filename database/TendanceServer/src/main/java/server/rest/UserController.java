package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.User;
import server.dao.UserDAO;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();


    @RequestMapping(method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam String username){
        User resultuser = null;
        try {
            resultuser = userDAO.getUserByUsername(username);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
        return resultuser;
    }
    //Pour récupérer la photo de profil
    @RequestMapping(value = "/{iduser}/profilpicture", method = RequestMethod.GET)
    public String getPictureOfUser(@PathVariable long iduser){
        User user = getUserById(iduser);
        File file = user.retrieveProfilePicture();
        return file.getPath();
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id){
        User result_user = null;
        try {
            result_user = userDAO.getUserByID(id);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
        return result_user;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        try {
            System.out.println(user);
            return userDAO.add_user(user);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long id){
        try {
            User usertodelete = userDAO.getUserByID(id);
            userDAO.del_user(usertodelete);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable long id, @RequestBody User user){
        user.setUserId(id);
        try {
            user = userDAO.update_user(user);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
        return user;
    }

    @RequestMapping(value = "{1}/friends", method = RequestMethod.GET)
    public List<User> getFriends(@PathVariable Long user_id){
        List<User> friends;
        try {
            return userDAO.getFriends(user_id);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private class InternalErrorException extends RuntimeException{
    }
}