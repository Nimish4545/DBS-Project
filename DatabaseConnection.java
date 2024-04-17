import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // Replace with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/TravelLIT";
        String user = "your_username";
        String password = "your_password";
        return DriverManager.getConnection(url, user, password);
    }
}