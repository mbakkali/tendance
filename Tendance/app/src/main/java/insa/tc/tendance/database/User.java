package insa.tc.tendance.database;

/**
 * Created by patrik on 18/05/16.
 */
public class User {
    private int id_user;
    private String username;
    private String mail;
    private String profilpicture;
    private String bio;
    private boolean male;
    private boolean publicprofil;
    private String phonenumber;


    public User(){
    }

    public User getMyProfil(){
        User me = new User();
        //On récupère les infos de l'utilisateur après authentification

        return me;
    }
    public int getId_user(){
        return id_user;
    }
}
