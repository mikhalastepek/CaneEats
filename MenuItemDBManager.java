package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.MenuItem;
import edu.bus.bte324.canehub.filemanagers.CustomerFileManager;
import edu.bus.bte324.canehub.infoclasses.AddressInfo;
import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class MenuItemDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = "root";
    private static final String SCHEMA_PASSWORD = "Purple77!"; //Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<MenuItem> getAllMenuItemsFromDB() throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<MenuItem> menuSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MENUITEM");



            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int rID = resultSet.getInt("RESTAURANT_ID");
                String mName = resultSet.getString("name");
                String mDescription = resultSet.getString("DESCRIPTION");
                double mPrice = resultSet.getDouble("PRICE");

                MenuItem m = new MenuItem(mName, mPrice, rID);  // New named menu item object
                m.setMenuItemDescription(mDescription); // The address object here is an unnamed object
                menuSet.add(m); //Adding from DB to the application HashSet
                System.out.println(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return menuSet;
        }
    }

    public HashSet<MenuItem> getMenuItemsByRestaurantFromDB(int restaurantID) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<MenuItem> menuSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MENUITEM WHERE RESTAURANT_ID = ?");

            ps.setInt(1, restaurantID);


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int rID = resultSet.getInt("RESTAURANT_ID");
                String mName = resultSet.getString("name");
                String mDescription = resultSet.getString("DESCRIPTION");
                double mPrice = resultSet.getDouble("PRICE");

                MenuItem m = new MenuItem(mName, mPrice, rID);  // New named menu item object
                m.setMenuItemDescription(mDescription); // The address object here is an unnamed object
                menuSet.add(m); //Adding from DB to the application HashSet
                System.out.println(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return menuSet;
        }
    }

    public HashSet<MenuItem> getMenuItemsFromDBbyName(String name) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<MenuItem> menuSet = new HashSet<>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MENUITEM WHERE NAME LIKE ?");

            ps.setString(1,"%"+name+"%");


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int rID = resultSet.getInt("RESTAURANTID");
                String mName = resultSet.getString("MENUITEMNAME");
                String mDescription = resultSet.getString("DESCRIPTION");
                double mPrice = resultSet.getDouble("PRICE");

                MenuItem m = new MenuItem(mName, mPrice, rID);  // New named menuitem object
                m.setMenuItemDescription(mDescription);

                menuSet.add(m); //Adding from DB to the application HashSet
                System.out.println(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return menuSet;
        }
    }

    public int getMenuItemIDFromDBbyRestaurantAndName(int restaurantID, String name) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int menuItemID = -1; // Initialize to -1 to indicate not found

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT item_id FROM `menuitem` WHERE restaurant_id = ? AND name like ?");

            ps.setInt(1, restaurantID);
            ps.setString(2, name);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                menuItemID = resultSet.getInt("item_id"); // Get int directly
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return menuItemID;
        }
    }

    public int insertSingleMenuItemIntoDB(MenuItem m) throws Exception
    {
        HashSet<MenuItem> mset = new HashSet<>();
        mset.add(m);
        return insertMenuItemsIntoDB(mset); //Wrapper
    }


    public int insertMenuItemsIntoDB(HashSet<MenuItem> menuSet) throws Exception
    {
        Class.forName(Driver_URL);

        Connection connection = null;

        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO MENUITEM VALUES (0,?,?,?,?)");

            for(MenuItem m : menuSet)
            {
                ps.setString(1, m.getMenuItemName());
                ps.setString(2,m.getMenuItemDescription());
                ps.setDouble(3,m.getPrice());
                ps.setInt(4,m.getRestaurantID());

                count += ps.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
            return count;
        }
    }

    public static void main(String[] args) throws Exception{

        MenuItemDBManager mdm = new MenuItemDBManager();

        mdm.getMenuItemsFromDBbyName("en");

        //cdm.insertCustomersIntoDB(customerSet);


    }

}
