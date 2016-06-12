package server.dao;

import server.SQLDatabase;
import server.Style;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StyleDAO {
    private Connection connection = SQLDatabase.connectDatabase();

    public List<Style> getStyles() throws SQLException {
        List<Style> styles = new ArrayList<>();
        Style style = null;
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM styles;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            style = new Style(rs.getLong("style_id"),
                              rs.getString("style_name"));
            styles.add(style);
        }
        return styles;
    }

    public Style addStyle(Style style) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("INSERT INTO styles(`style_name`) VALUE (?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,style.getStyle_name());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            style.setStyle_id(rs.getLong(1));
        return style;
    }
}
