package server.rest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.Clothe;
import server.Outfit;
import server.dao.OutfitDAO;
import server.dao.UserDAO;
import server.moteur.PropositionLook;
import server.moteur.Tenue;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Patrik on 06/06/2016.
 */
@RestController
@RequestMapping(value = "/outfit")


public class OutfitController {

    private OutfitDAO outfitDAO = new OutfitDAO();
    private PropositionLook propositionLook = new PropositionLook();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Outfit> getOutfitsByOwner(@RequestParam long owner) {
        try {
            List<Outfit> outfits = outfitDAO.getOutfitsByUser(owner);
            return outfits;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Clothe> getClothesOfOutfits(@PathVariable long id) {
        try {
            List<Clothe> clothes = outfitDAO.getClothesOfOutfit(id);
            return clothes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException();
        }
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public List<Outfit> getFavoriteByOwner(long owner){
        List<Outfit> favorites = new ArrayList<>();
        favorites.add(new Outfit(1,"petit outfit posé", "Outfit 1", null, 1));
        return favorites;
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long id){
        Outfit outfitToDelete = OutfitDAO.getOutfitByID(id);
        outfitDAO.del_outfit(outfitToDelete);
    }

    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public void updateOutfit(@RequestBody Outfit outfit){
        OutfitDAO.update_outfit(outfit);
    }

    @RequestMapping(value = "/suggestion/{sexe}/{event}", method = RequestMethod.GET)
    public List<List> suggestOutfit(@PathVariable int sexe, @PathVariable int event){

        List<List> retour = new ArrayList<>();
        Map<Float, Tenue> suggestion = propositionLook.proposerLook(sexe, event, 20);
        for (Tenue tenue : suggestion.values()) {
            retour.add(tenue.toJson());
        }
        return retour;
    }
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    public Outfit addOutfit(@RequestBody Outfit outfit,
                      @RequestBody MultipartFile selfie) {
        String name = UUID.randomUUID().toString();
        if (!selfie.isEmpty()) {
            try {
                File outputFile = new File(Outfit.ROOT + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outputFile));
                FileCopyUtils.copy(selfie.getInputStream(), stream);
                stream.close();
                outfitDAO.add_outfit(outfit);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outfit;
    }

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        private class InternalErrorException extends RuntimeException{
        }
}
