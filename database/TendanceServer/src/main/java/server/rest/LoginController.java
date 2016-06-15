package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.User;
import server.dao.UserDAO;

import java.sql.SQLException;


@RestController
public class LoginController {

    private UserDAO userDAO = new UserDAO();

    //http://localhost:8080/login/mail=%camille@insa.fr&password=%cemonet
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User login(@RequestParam String mail, @RequestParam String password) {
        User user = null;
        try {
            System.out.println(mail + " " + password);

            user = userDAO.getUserByMailAndPassword(mail,password);
            System.out.println(user);
            return user;
        } catch (SQLException e) {
            throw new ForbiddenException();
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    private class ForbiddenException extends RuntimeException{
    }
}
