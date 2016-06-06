package server.rest;

import org.springframework.web.bind.annotation.*;
import server.Outfit;
import server.User;

@RequestMapping("/user")
@RestController
public class UserController {

    private final int counter = 0; 

    @RequestMapping(method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value="username", defaultValue="myusername") String username) {

    	/*counter.incrementAndGet(),"mehdi","test@mail.com","/img/mehdi.jpeg","bio",true,true,,"passwd" */

        return null; 
       
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        //Connection connection = SQLDatabase.ConnectDatabase();
        //JdbcUserDAO.add_user(connection,user);
        return null;

    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id){
        return "ok";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable long id, @RequestBody User user){
        return user;
    }

    @RequestMapping(value = "/outfit/add", method = RequestMethod.POST)
    public Outfit insertOutfit(@RequestBody Outfit outfit){
        //TODO
        return outfit;
    }

}