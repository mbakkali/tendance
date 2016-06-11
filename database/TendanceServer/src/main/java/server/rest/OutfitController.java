package server.rest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.Clothe;
import server.Outfit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Patrik on 06/06/2016.
 */
@RestController
@RequestMapping(value = "/outfit")


public class OutfitController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Outfit> getOutfitsByOwner(@RequestParam long owner) {
        List<Outfit> outfits = new ArrayList<>();

        return outfits;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public List<Outfit> getFavoriteByOwner(long owner){
        List<Outfit> favorites = new ArrayList<>();
        favorites.add(new Outfit(1,"petit outfit posé", "Outfit 1", null, 1));
        return favorites;
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
                //AddToDB

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outfit;
    }
}
