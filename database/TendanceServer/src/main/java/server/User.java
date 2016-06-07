package server;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private long user_id;
    private String username;
    private String mail;
    private String profilpicture;
    private String bio;
    private boolean male;
    private boolean priv;
    private String phone;

    @JsonIgnore
    private String password;

    private String age;


    public User(long user_id,
                String username, 
                String mail, 
                String profilpicture, 
                String bio, 
                boolean male,
                boolean priv, 
                String phone,
                String age) {

        this.user_id = user_id;
        this.username = username;
        this.mail = mail; 
        this.profilpicture = profilpicture; 
        this.bio = bio; 
        this.male= male;
        this.priv=priv;
        this.age = age;
        this.phone = phone;
    }

    public User(String username, String mail, String profilpicture, String bio, boolean male, boolean priv, String phone, String age, String password) {
        this.username = username;
        this.mail = mail;
        this.profilpicture = profilpicture;
        this.bio = bio;
        this.male = male;
        this.priv = priv;
        this.phone = phone;
        this.password = password;
        this.age = age;
    }




    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getProfilpicture() {
        return profilpicture;
    }

    public String getBio() {
        return bio;
    }

    public boolean isMale() {
        return male;
    }

    public boolean isPriv() {
        return priv;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", profilpicture='" + profilpicture + '\'' +
                ", bio='" + bio + '\'' +
                ", male=" + male +
                ", priv=" + priv +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}












