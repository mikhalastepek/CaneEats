package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class RestaurantDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = "root";
    private static final String SCHEMA_PASSWORD = "Purple77!"; //Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<Restaurant> getAllRestaurantsFromDB() throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Restaurant> restaurantSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESTAURANT");



            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String rID = resultSet.getString("RESTAURANT_ID");
                String rName = resultSet.getString("NAME");
                String rLocation = resultSet.getString("LOCATION");
                String rCuisine = resultSet.getString("CUISINE");

                Restaurant r = new Restaurant(rName, rCuisine);  // New named Restaurant object
                r.setRestaurantLocation(rLocation); // The address object here is an unnamed object

                restaurantSet.add(r); //Adding from DB to the application HashSet
                System.out.println(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return restaurantSet;
        }
    }

    public HashSet<Restaurant> getRestaurantsFromDBbyName(String name) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Restaurant> restaurantSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESTAURANT WHERE NAME LIKE ?");

            ps.setString(1,"%"+name+"%");


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String rID = resultSet.getString("RESTAURANT_ID");
                String rName = resultSet.getString("NAME");
                String rCuisine = resultSet.getString("CUISINE");
                String rLocation = resultSet.getString("LOCATION");

                Restaurant r = new Restaurant(rName, rCuisine);  // New named restaurant object
                r.setRestaurantLocation(rLocation);

                restaurantSet.add(r); //Adding from DB to the application HashSet
                System.out.println(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return restaurantSet;
        }
    }

    public int getRestaurantIDFromDBbyName(String name) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int restaurantID = -1; // Initialize to -1 to indicate not found

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT RESTAURANT_ID FROM RESTAURANT WHERE NAME LIKE ?");

            ps.setString(1, "%" + name + "%");

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                restaurantID = resultSet.getInt("RESTAURANT_ID"); // Get int directly
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return restaurantID;
        }
    }


    public int insertSingleRestaurantIntoDB(Restaurant r) throws Exception
    {
        HashSet<Restaurant> rset = new HashSet<>();
        rset.add(r);
        return insertRestaurantsIntoDB(rset); //Wrapper
    }


    public int insertRestaurantsIntoDB(HashSet<Restaurant> restaurantSet) throws Exception
    {
        Class.forName(Driver_URL);

        Connection connection = null;

        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO RESTAURANT VALUES (0,?,?,?)");

            for(Restaurant r : restaurantSet)
            {
                ps.setString(1,r.getRestaurantName());
                ps.setString(2,r.getRestaurantLocation());
                ps.setString(3,r.getCuisine());

                count += ps.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return count;
        }
    }

    public int updateRestaurantInDB(Restaurant restaurant, String nameToUpdate) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("UPDATE RESTAURANT SET NAME = ?, LOCATION = ?, CUISINE = ?  WHERE RESTAURANT_ID = ?");

            // Set values for update based on the restaurant object
            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getRestaurantLocation());
            ps.setString(3, restaurant.getCuisine());

            // Specify the restaurant ID for update based on a search or selection criteria
            ps.setInt(4, getRestaurantIDFromDBbyName(nameToUpdate));  // Update based on restaurant ID

            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
            return count;
        }
    }

    public int removeRestaurantFromDB(String nameToDelete) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int rowsDeleted = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM RESTAURANT WHERE NAME = ?");

            // Specify the customer ID for deletion based on the customer object
            ps.setString(1, nameToDelete);

            rowsDeleted = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return rowsDeleted;
        }
    }

    public static void main(String[] args) throws Exception{

        RestaurantDBManager rdm = new RestaurantDBManager();

        rdm.getRestaurantsFromDBbyName("en");

        //cdm.insertRestaurantsIntoDB(restaurantSet);


    }

}
