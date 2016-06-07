package server;


public class Outfit {

    private long outfit_id;
    private String timestamp;
    private String description;
    private long style_id;
    private long likes;

    public Outfit(long outfit_id, String timestamp, String description, long style_id, long likes) {
        this.outfit_id = outfit_id;
        this.timestamp = timestamp;
        this.description = description;
        this.style_id = style_id;
        this.likes = likes;
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
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }
}
