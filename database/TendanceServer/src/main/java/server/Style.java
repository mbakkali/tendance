package server;

public class Style {
    private long style_id;
    private String style_name;

    public Style(long style_id, String style_name) {
        this.style_id = style_id;
        this.style_name = style_name;
    }

    public void setStyle_id(long style_id) {
        this.style_id = style_id;
    }

    public String getStyle_name() {
        return style_name;
    }
}
