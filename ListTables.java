package edu.bus.bte324.canehub;


import java.sql.*;

public class ListTables {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Replace these with your connection details
        String url = "jdbc:mysql://localhost:3306/caneeats";
        String user = "root";
        String password = "Purple77!";

        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection connection = DriverManager.getConnection(url, user, password);

        try {
            // Get metadata about the database
            DatabaseMetaData metadata = connection.getMetaData();

            // Get information about all tables in the database
            ResultSet resultSet = metadata.getTables(null, null, "%", null);

            // Print table names
            System.out.println("Tables in the database:");
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);
            }

        } finally {
            // Close the connection
            connection.close();
        }
    }
}
