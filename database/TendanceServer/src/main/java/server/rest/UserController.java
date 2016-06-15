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
    public List<User> getUsersLikeUsername(@RequestParam String username){
        List<User> resultuser = null;
        try {
            return resultuser = userDAO.getUsersByUsername(username);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
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

    @RequestMapping(value = "/friend/{id1}/{id2}", method = RequestMethod.POST)
    public void addfriend(@PathVariable long id1 ,@PathVariable long id2){
        try {
            User user1 = userDAO.getUserByID(id1);
            User user2 = userDAO.getUserByID(id2);

            userDAO.addFriend(user1,user2);

        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }

    @RequestMapping(value = "/friend/{id1}/{id2}", method = RequestMethod.DELETE)
    public void delfriend(@PathVariable long id1, @PathVariable long id2){
        try {

            User user1 = userDAO.getUserByID(id1);
            User user2 = userDAO.getUserByID(id2);
            userDAO.delFriend(user1,user2);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }
    @RequestMapping(value = "/isfriend/{id1}/{id2}", method = RequestMethod.POST)
    public boolean isfriend(@PathVariable long id1, @PathVariable long id2){
        try {
            boolean bool = false;
            User user1 = userDAO.getUserByID(id1);
            User user2 = userDAO.getUserByID(id2);
            bool = userDAO.isFriended(user1,user2);
            return bool;
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

    @RequestMapping(value = "{user_id}/friends", method = RequestMethod.GET)
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