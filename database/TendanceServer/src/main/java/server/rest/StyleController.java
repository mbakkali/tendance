package server.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrik on 06/06/2016.
 */
@RequestMapping(value = "/style")
@RestController
public class StyleController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Style> getStyles(){
        List<Style> styles = new ArrayList<>();



        return styles;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Style insert(@RequestBody Style style){
        return style;
    }

}
