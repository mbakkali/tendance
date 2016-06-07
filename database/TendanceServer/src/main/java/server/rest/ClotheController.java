package server.rest;

import org.springframework.web.bind.annotation.*;
import server.Clothe;
import server.Type;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Patrik on 07/06/2016.
 */

@RestController
@RequestMapping(value = "/clothe")
public class ClotheController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<Type,List<Clothe>> getMyCloth(long owner){
        Map<Type, List< Clothe>> clothes = new Hashtable<>();

        return clothes;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Clothe addClothe(){
        //TODO
        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteClothe(@PathVariable long id){
        //TODO
    }


}
