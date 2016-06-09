package server.rest;

import org.springframework.web.bind.annotation.*;
import server.Outfit;
import server.User;
import server.dao.UserDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private UserDAO userDAO = new UserDAO();
    private final int counter = 0; 

    @RequestMapping(method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam String username) {
    	/*counter.incrementAndGet(),"mehdi","test@mail.com","/img/mehdi.jpeg","bio",true,true,,"passwd" */
        return null;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id){
        //TODO
        return null;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(){

        //TODO

        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        userDAO.add_user(user);

        return user;

    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id){
        //TODO
        return "ok";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable long id, @RequestBody User user){
        //TODO
        return user;
    }

    @RequestMapping(value = "/outfit/add", method = RequestMethod.POST)
    public Outfit insertOutfit(@RequestBody Outfit outfit){
        //TODO
        return outfit;
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public List<User> getFriends(@RequestParam long iduser){

        List<User> friends = new ArrayList<>();
        friends.add(new User(1,"pfortier", "pfortier@insa-lyon.fr", "null", "Je suis patrik", true,
                false, "0678787878", "1994-01-10", "tendance"));
        friends.add(new User(2,"cemonet", "cemonet@insa-lyon.fr", "null", "Je suis Camille", false, false,
                "0679797979", "1994-05-20", "tendance"));
        //TODO request from db

        return friends;
    }

}