package server.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.dao.UserDAO;

/**
 * Created by Patrik on 09/06/2016.
 */

@RestController
public class LoginController {

    private UserDAO userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public long login(@RequestParam String mail, @RequestParam String password) {
        long user_id = 0 ;
        try {
            //TODO
        }catch (NullPointerException e){
            user_id = 0;
            System.err.println("Error BDD");
        }
        return user_id;
        //REQUETE POUR SAVOIR SI LE COUPLE LOGIN MOT DE PASSE DONNE UN RESULTAT, on renvoie l'ID de l'utilisateur pour qu'in récupère son profil
    }
}
