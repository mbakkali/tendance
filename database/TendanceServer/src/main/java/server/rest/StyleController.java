package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.Style;
import server.dao.StyleDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrik on 06/06/2016.
 */
@RequestMapping(value = "/style")
@RestController
public class StyleController {
    StyleDAO styleDAO = new StyleDAO();
    @RequestMapping(method = RequestMethod.GET)
    public List<Style> getStyles(){
        try {
            return styleDAO.getStyles();
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Style insert(@RequestBody Style style){

        return style;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private class InternalErrorException extends RuntimeException{
    }
}
