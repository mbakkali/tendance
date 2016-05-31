package server;

public class User {

    private final long user_id;
    private final String username;

    public User(long user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public long getuser_id() {
        return user_id;
    }

    public String getusername() {
        return username;
    }
}
