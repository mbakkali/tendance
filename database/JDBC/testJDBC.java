import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class testJDBC {

  public static void main(String[] argv) {

	System.out.println("--------Test driver MySQL JDBC  ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Attention pas de driver JDBC trouvé");
		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver est bon !");
	Connection connection = null;

	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://90.66.114.198:3306","monitor", "tendance2016");

	} catch (SQLException e) {
		System.out.println("Problème de connexion ! ");
		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("La base de données est connectée");
	} else {
		System.out.println("Problème de connexion avec la database!");
	}
  }
}