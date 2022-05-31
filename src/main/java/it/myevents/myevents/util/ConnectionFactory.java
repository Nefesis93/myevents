package it.myevents.myevents.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/myevents";

    private static final String USER = "postgres";

    private static final String PASSWORD = "dacatafg11";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // System.out.println("connessione al db stabilita");
        } catch (SQLException e) {
            System.out.println("connessione al db fallita");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null)
            try {
                connection.close();
                connection = null;
                // System.out.println("connessione al db chiusa correttamente");
            } catch (SQLException e) {
                System.out.println("non è stato possibile chiudere la connessione con il db");
                e.printStackTrace();
            }
    }
}