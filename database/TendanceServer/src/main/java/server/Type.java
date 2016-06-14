package server;


public class Type {
    private long type_id;
    private String type_name;

    public Type(long type_id, String type_name) {
        this.type_id = type_id;
        this.type_name = type_name;
    }

    public Type(String type_name) {
        this.type_name = type_name;
    }

    public long getType_id() {
        return type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }
}
