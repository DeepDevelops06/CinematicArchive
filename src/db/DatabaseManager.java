package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // This will create a file named 'cinematic_archive.db' in your project root
    private static final String URL = "jdbc:sqlite:cinematic_archive.db";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: SQLite JDBC Driver not found. Check your lib folder.");
        } catch (SQLException e) {
            System.out.println("Database Connection Error: " + e.getMessage());
        }
        return connection;
    }

    // Automatically builds the schema on the first run
    public static void initializeDatabase() {
        String createFilmsTable = "CREATE TABLE IF NOT EXISTS films ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "director TEXT,"
                + "genre TEXT,"
                + "release_year INTEGER);";

        String createReviewsTable = "CREATE TABLE IF NOT EXISTS reviews ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "film_id INTEGER,"
                + "user_id INTEGER,"
                + "rating REAL,"
                + "comment TEXT,"
                + "FOREIGN KEY (film_id) REFERENCES films(id));";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            if (conn != null) {
                stmt.execute(createFilmsTable);
                stmt.execute(createReviewsTable);
                // System.out.println("Database verified/initialized successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}