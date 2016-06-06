package server.rest;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.User;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;

import java.text.*;





@RestController
public class UserController {

    private final int counter = 0; 


    @RequestMapping("/user")
    public User myuser(@RequestParam(value="username", defaultValue="myusername") String username) {

    	

    	/*counter.incrementAndGet(),"mehdi","test@mail.com","/img/mehdi.jpeg","bio",true,true,,"passwd" */

        return null; 
       
    }

}