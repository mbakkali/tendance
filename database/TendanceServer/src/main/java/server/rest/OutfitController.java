package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.Clothe;
import server.Outfit;
import server.User;
import server.dao.OutfitDAO;
import server.moteur.PropositionLook;
import server.moteur.Tenue;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public List<Outfit> getFavoriteByOwner(List<User> user_list){

      try {
          List<Outfit> outfit_list = new ArrayList<>();
          Outfit outfit ;
          for (int i = 0; i < outfit_list.size(); i++) {
              outfit = outfitDAO.getOutfitsByUser(i).get(i);
              user_list.get(i);
              outfit_list.add(outfit);
          }
          return outfit_list;
      }
      catch (SQLException e){
          e.printStackTrace();
          throw new InternalErrorException();
      }
    }






    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long id){
        Outfit outfitToDelete = OutfitDAO.getOutfitByID(id);
        OutfitDAO.del_outfit(outfitToDelete);
    }

    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public void updateOutfit(@RequestBody Outfit outfit){
        try {
            OutfitDAO.update_outfit(outfit);
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new InternalErrorException();
        }

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

                outfitDAO.addSelfieToOutfit(outfit,selfie);
                OutfitDAO.update_outfit(outfit);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new InternalErrorException();
        }

        }
        return outfit;
    }

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        private class InternalErrorException extends RuntimeException{
        }
}
