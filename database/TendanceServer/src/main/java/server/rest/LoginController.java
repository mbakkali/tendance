package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.User;
import server.dao.UserDAO;

import java.sql.SQLException;

/**
 * Created by Patrik on 09/06/2016.
 */

@RestController
public class LoginController {

    private UserDAO userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User login(@RequestParam String mail, @RequestParam String password) {
        User user = null;
        try {
            user = userDAO.getUserByMailAndPassword(mail,password);
            if(user==null)
                throw new ForbiddenException();
            return user;
        } catch (SQLException e) {
            throw new ForbiddenException();
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    private class ForbiddenException extends RuntimeException{
    }
}
