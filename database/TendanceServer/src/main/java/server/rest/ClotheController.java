package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import server.*;
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


    //pas testé les fonctions encore
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, List<Clothe>> getAllClothOfUser(User user) {
        Map<String, List<Clothe>> myClothesByType = new HashMap<>();
        try {
            List<Type> types = clotheDAO.getAllTypes();
            for (Type type : types) {
                // myClothesByType.put(type.getType_name(),clotheDAO.getClothesOfOwnerForType(user, type));
            }
            return myClothesByType;
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }


    @RequestMapping(value = "/clotheof/{id}", method = RequestMethod.GET)
    public List<Clothe> getAllClotheOfuser(@PathVariable long id) {
        List<Clothe> clothes = null;
        try {
            clothes = clotheDAO.getClothesOfOwner(id);
        } catch (SQLException e) {
            System.out.println("Problème dans Get all clothe of USer");
            throw new InternalErrorException();
        }
        return clothes;

    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteClothe(@PathVariable long id) {
        try {
            clotheDAO.del_clothe(id);
        } catch (SQLException e) {
            System.out.println("Problème dans del_Clothes");
            throw new InternalErrorException();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void add(@RequestBody ClotheWithFile clothe) {
        String name = UUID.randomUUID().toString();
        File file = clothe.getFile();
        try {
            File outputFile = new File(Clothe.ROOT + "/" + name);
            //TODO Probleme here
            FileCopyUtils.copy(file, outputFile);
            clothe.setClothe_photo(outputFile.getPath());
            clotheDAO.add_clothe(clothe);
        } catch (Exception e) {
            System.out.println("Problème dans add_Clothes " + e);

            throw new InternalErrorException();
        }

    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<Type> getTypes(){
        try {
            return clotheDAO.getAllTypes();
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private class InternalErrorException extends RuntimeException{
    }

}
