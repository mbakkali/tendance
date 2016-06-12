package server;


import server.dao.OutfitDAO;

import java.security.Timestamp;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

public class Outfit {

    OutfitDAO outfitDAO = new OutfitDAO();

    public static final String ROOT = "outfits";
    private long outfit_id;
    private String timestamp;
    private String description;
    private String photo;
    private long style_id;
    private long likes;
    private long user_id;
    private Set<Clothe> outfit_clothes;

    //Constructeur plus petit pour OutfitController - patrik
    public Outfit(long outfit_id, String timestamp, String description,String photo, long style_id) {
        this.outfit_id = outfit_id;
        this.timestamp = timestamp;
        this.photo = photo;
        this.description = description;
        this.style_id = style_id;
        this.likes = getLikes();
    }


    public Outfit(long outfit_id, String description, String photo, long style_id) {
        this.outfit_id = outfit_id;
        this.description = description;
        this.photo = photo;
        this.timestamp = new Timestamp(new Date(), null).toString();
        this.style_id = style_id;

    }

    //Constructeur défini par Mehdi pour GetOutfitbyID
    public Outfit(long outfit_id, String timestamp, String description, String photo, long style_id, long likes,long user_id) {
        this.outfit_id = outfit_id;
        this.timestamp = timestamp;
        this.description = description;
        this.photo = photo;
        this.style_id = style_id;
        this.likes = getLikes();
        this.user_id = getUser_id();
    }


    public long getOutfit_id() {
        return outfit_id;
    }

    public void setOutfit_id(long outfit_id) {
        this.outfit_id = outfit_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStyle_id() {
        return style_id;
    }

    public void setStyle_id(long style_id) {
        this.style_id = style_id;
    }

    public long getLikes() {
        try {
            return outfitDAO.get_likes(this);
        } catch (SQLException e) {
            return 0;
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
