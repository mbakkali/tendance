package server.rest;

import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import server.Clothe;
import server.Type;
import server.User;
import server.dao.ClotheDAO;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Patrik on 07/06/2016.
 */

@RestController
@RequestMapping(value = "/clothe")
public class ClotheController {
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, List<Clothe>> getAllClothOfUser(User user) {
        Map<String, List<Clothe>> myClothesByType = new HashMap<>();
        try {
            List<Type> types = ClotheDAO.getAllTypes();
            for (Type type: types) {
                myClothesByType.put(type.getType_name(),ClotheDAO.getClothesOfOwnerForType(user, type));
            }
            return  myClothesByType;
        } catch (SQLException e) {
            //TODO REPLACE WITH ERROR 500
            return null;
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteClothe(@PathVariable long id){
        //TODO
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Clothe add( @RequestBody Clothe clothe,
                       @RequestBody MultipartFile file) {
        String name = UUID.randomUUID().toString();
        if (!file.isEmpty()) {
            try {
                File outputFile = new File(Clothe.ROOT + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outputFile));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                //AddToDB


           } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clothe;
    }


}
