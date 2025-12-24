import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) {
        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS staging";
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Database 'staging' created successfully or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}