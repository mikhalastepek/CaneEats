package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.entities.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;

public class OrderDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = "root";
    private static final String SCHEMA_PASSWORD = "Purple77!"; //Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<Order> getAllOrdersFromDB() throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Order> orderSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Order`");



            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int oID = resultSet.getInt("ORDER_ID");
                int cID = resultSet.getInt("CUSTOMER_ID");
                int rID = resultSet.getInt("RESTAURANT_ID");
                String oDate = resultSet.getString("ORDER_DATE");

                Order o = new Order(cID,rID);  // New named Restaurant object
                o.setOrderDate(oDate); // The address object here is an unnamed object

                orderSet.add(o); //Adding from DB to the application HashSet
                System.out.println(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return orderSet;
        }
    }

    public HashSet<Order> getOrdersFromDBbyOrderID(int orderID) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Order> orderSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Order` WHERE ORDER_ID LIKE ?");

            ps.setString(1,"%"+orderID+"%");


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int rID = resultSet.getInt("RESTAURANT_ID");
                int cID = resultSet.getInt("CUSTOMER_ID");
                String oDate = resultSet.getString("ORDER_DATE");


                Order o = new Order(cID, rID);  // New named restaurant object
                o.setOrderDate(oDate);

                orderSet.add(o); //Adding from DB to the application HashSet
                System.out.println(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return orderSet;
        }
    }

    public HashSet<Order> getOrdersFromDBbyCustomerID(int customerID) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Order> orderSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Order` WHERE CUSTOMER_ID LIKE ?");

            ps.setString(1,"%"+customerID+"%");


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int rID = resultSet.getInt("RESTAURANT_ID");
                int cID = resultSet.getInt("CUSTOMER_ID");
                String oDate = resultSet.getString("ORDER_DATE");


                Order o = new Order(cID, rID);  // New named restaurant object
                o.setOrderDate(oDate);

                orderSet.add(o); //Adding from DB to the application HashSet
                System.out.println(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return orderSet;
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

    public int getOrderIDFromDBbyRestaurantAndCustomer(int restaurantID, int customerID) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int orderID = -1; // Initialize to -1 to indicate not found

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT ORDER_ID FROM `Order` WHERE restaurant_id = ? AND customer_id = ?");

            ps.setInt(1, restaurantID);
            ps.setInt(2, customerID);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                orderID = resultSet.getInt("ORDER_ID"); // Get int directly
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return orderID;
        }
    }

    public int getCustomerIDFromDBbyName(String name) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int customerID = -1; // Initialize to -1 to indicate not found

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMERNAME LIKE ?");

            ps.setString(1, "%" + name + "%");

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                customerID = resultSet.getInt("CUSTOMER_ID"); // Get int directly
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return customerID;
        }
    }


    public int insertSingleOrderIntoDB(Order o) throws Exception
    {
        HashSet<Order> orderSet = new HashSet<>();
        orderSet.add(o);
        return insertOrdersIntoDB(orderSet); //Wrapper
    }


    public int insertOrdersIntoDB(HashSet<Order> orderSet) throws Exception
    {
        Class.forName(Driver_URL);

        Connection connection = null;

        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `Order` VALUES (0,?,?,?)");

            for(Order o : orderSet)
            {
                ps.setInt(1,o.getCustomerID());
                ps.setInt(2,o.getRestaurantID());
                ps.setString(3, o.getOrderDate());

                count += ps.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return count;
        }
    }

    public static void main(String[] args) throws Exception{

        OrderDBManager odm = new OrderDBManager();

        odm.getAllOrdersFromDB();

        //cdm.insertRestaurantsIntoDB(restaurantSet);


    }

}
