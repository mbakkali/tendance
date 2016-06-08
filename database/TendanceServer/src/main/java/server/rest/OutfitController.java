package server.rest;

import org.springframework.web.bind.annotation.*;
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
    public List<Outfit> getOutfitByOwner(@RequestParam long owner) {
        List<Outfit> outfits = new ArrayList<>();
        //TODO
        return outfits;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public List<Outfit> getFavoriteByOwner(long owner){
        List<Outfit> favorites = new ArrayList<>();
        favorites.add(new Outfit(1,"petit outfit posé", "Outfit 1", null, 1));
        return favorites;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Outfit addOutfit(@RequestBody Outfit outfit ){

        return outfit;
    }
}
