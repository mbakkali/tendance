package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.dao.UserDAO;

import java.sql.Connection;

import static server.SQLDatabase.ConnectDatabase;
/*
* Cette classe va disparaitre quand on aura plus besoin de fiare des tests
 */

public class Main {

	public static void main(String[] args) {
		ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    	User utilisateur1 = new User("utilisateur3","utilisateur1@insamail.com","/img/utilisateur1","bio1",true,true,"0637263716","1994-02-14","utilisateur1password");
 		System.out.println("utilisateur1 crée");
        userDAO.insert(utilisateur1);


        User user = userDAO.findByUserId(1);
        System.out.println(user);

    	//DisplayTable(connection,"users")
    }

//Connexion à la base de donnée du serveur 



}