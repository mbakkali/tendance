package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import server.Clothe;
import server.Outfit;
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
    ClotheDAO clotheDAO = new ClotheDAO();

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, List<Clothe>> getAllClothOfUser(User user) {
        Map<String, List<Clothe>> myClothesByType = new HashMap<>();
        try {
            List<Type> types = clotheDAO.getAllTypes();
            for (Type type: types) {
                myClothesByType.put(type.getType_name(),clotheDAO.getClothesOfOwnerForType(user, type));
            }
            return  myClothesByType;
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteClothe(@PathVariable long id){
        try {
            clotheDAO.del_clothe(id);
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
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


           } catch (Exception e){
                throw new InternalErrorException();
            }
        }
        return clothe;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private class InternalErrorException extends RuntimeException{
    }

}
