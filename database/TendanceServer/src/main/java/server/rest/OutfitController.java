package server.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.Outfit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrik on 06/06/2016.
 */
@RestController
@RequestMapping(value = "/outfit")
public class OutfitController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Outfit> getOutfitByOwner(long owner) {
        List<Outfit> outfits = new ArrayList<>();
        //TODO
        return outfits;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public List<Outfit> getFavoriteByOwner(long owner){
        List<Outfit> favorites = new ArrayList<>();

        return favorites;
    }
}
