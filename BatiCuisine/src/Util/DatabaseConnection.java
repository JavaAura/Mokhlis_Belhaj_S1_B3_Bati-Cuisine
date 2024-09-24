package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class for managing database connections.
 * This class provides methods to obtain and close database connections.
 */
public class DatabaseConnection {
    private static final Properties props = new Properties();
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        try (FileInputStream input = new FileInputStream("C:/Users/yc/Desktop/AURA/Mokhlis_Belhaj_S1_B3_Bati-Cuisine/BatiCuisine/Resources/config.properties")) {
            props.load(input);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (IOException ex) {
            throw new ExceptionInInitializerError("Failed to load database configuration: " + ex.getMessage());
        }
    }

    private static volatile DatabaseConnection instance;
    private Connection connection;

    /**
     * Private constructor to prevent instantiation.
     */
    private DatabaseConnection() {}

    /**
     * Returns the singleton instance of DatabaseConnection.
     *
     * @return The singleton instance of DatabaseConnection.
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Gets a connection to the database.
     * If a connection doesn't exist or is closed, a new one is created.
     *
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs.
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    /**
     * Closes the current database connection if it exists and is open.
     *
     * @throws SQLException If a database access error occurs.
     */
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}
