package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.infoclasses.AddressInfo;
import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class CustomerDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = "root";
    private static final String SCHEMA_PASSWORD = "Purple77!"; //Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<Customer> getAllCustomersFromDB() throws Exception {
        Class.forName(Driver_URL);

        HashSet<Customer> customersSet = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMER");


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                resultSet.getString("CUSTOMERID");
                String cName = resultSet.getString("CUSTOMERNAME");
                String cPhone =
                        resultSet.getString("CUSTOMERPHONE");
                String CustomerAddress_FirstLine = resultSet.getString(4);
                String CustomerAddress_SecondLine = resultSet.getString(5);
                String CustomerAddress_State = resultSet.getString(6);
                String CustomerAddress_City = resultSet.getString(7);
                String CustomerAddress_Zip = resultSet.getString(8);
                String Customer_Payment_CardNumber = resultSet.getString(9);
                String Customer_Payment_CardName = resultSet.getString(10);
                String Customer_Payment_CardExpiry = resultSet.getString(11);
                int Customer_Payment_CardCVV = Integer.parseInt(resultSet.getString(12).trim());

                Customer c = new Customer(cName, cPhone);  // New named customer object
                c.setCustomerAddress(new AddressInfo("NoStreet", CustomerAddress_FirstLine, CustomerAddress_SecondLine, CustomerAddress_State, CustomerAddress_City, CustomerAddress_Zip)); // The address object here is an unnamed object
                c.setCustomerPayment(new PaymentInfo(Customer_Payment_CardNumber, Customer_Payment_CardName, "VISA", Customer_Payment_CardCVV, Customer_Payment_CardExpiry));

                customersSet.add(c); //Adding from DB to the application HashSet
                System.out.println(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return customersSet;
        }
    }

    public HashSet<Customer> getCustomersFromDBbyName(String name) throws Exception {
        Class.forName(Driver_URL);

        HashSet<Customer> customersSet = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTOMERNAME LIKE ?");

            ps.setString(1, "%" + name + "%");


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                resultSet.getString("CUSTOMER_ID");
                String cName = resultSet.getString("CUSTOMERNAME");
                String cPhone = resultSet.getString("CUSTOMERPHONE");
                String CustomerAddress_FirstLine = resultSet.getString(4);
                String CustomerAddress_SecondLine = resultSet.getString(5);
                String CustomerAddress_State = resultSet.getString(6);
                String CustomerAddress_City = resultSet.getString(7);
                String CustomerAddress_Zip = resultSet.getString(8);
                String Customer_Payment_CardNumber = resultSet.getString(9);
                String Customer_Payment_CardName = resultSet.getString(10);
                String Customer_Payment_CardExpiry = resultSet.getString(11);
                int Customer_Payment_CardCVV = Integer.parseInt(resultSet.getString(12).trim());

                Customer c = new Customer(cName, cPhone);  // New named customer object
                c.setCustomerAddress(new AddressInfo("NoStreet", CustomerAddress_FirstLine, CustomerAddress_SecondLine, CustomerAddress_State, CustomerAddress_City, CustomerAddress_Zip)); // The address object here is an unnamed object
                c.setCustomerPayment(new PaymentInfo(Customer_Payment_CardNumber, Customer_Payment_CardName, "VISA", Customer_Payment_CardCVV, Customer_Payment_CardExpiry));

                customersSet.add(c); //Adding from DB to the application HashSet
                System.out.println(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return customersSet;
        }
    }

    public int insertSingleCustomerIntoDB(Customer c) throws Exception
    {
        HashSet<Customer> cset = new HashSet<>();
        cset.add(c);
        return insertCustomersIntoDB(cset); //Wrapper
    }


    public int insertCustomersIntoDB(HashSet<Customer> customerSet) throws Exception
         {
            Class.forName(Driver_URL);

             int count = 0;
             try (Connection connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD)) {
                 PreparedStatement ps = connection.prepareStatement("INSERT INTO CUSTOMER VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?)");

                 for (Customer c : customerSet) {
                     ps.setString(1, c.getCustomerName());
                     ps.setString(2, c.getPhoneNumber());
                     ps.setString(3, c.getCustomerAddress().getLine1Address());
                     ps.setString(4, c.getCustomerAddress().getLine2Address());
                     ps.setString(5, c.getCustomerAddress().getState());
                     ps.setString(6, c.getCustomerAddress().getCity());
                     ps.setString(7, c.getCustomerAddress().getZipCode());
                     ps.setString(8, c.getCustomerPayment().getCardNumber());
                     ps.setString(9, c.getCustomerPayment().getCardName());
                     ps.setString(10, c.getCustomerPayment().getExpDate());
                     ps.setInt(11, c.getCustomerPayment().getCvvCode());
                     ps.setString(12, c.getCustomerPayment().getCardType());

                     count += ps.executeUpdate();

                 }

             } catch (Exception e) {
                 e.printStackTrace();
             } finally {
                 return count;
             }
    }

    public int getCustomerIDFromDBbyName(String name) throws Exception {
        Class.forName(Driver_URL);

        int customerID = -1; // Initialize to -1 to indicate not found
        try (Connection connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("SELECT CUSTOMER_ID FROM customer WHERE customerNAME LIKE ?");

            ps.setString(1, "%" + name + "%");

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                customerID = resultSet.getInt("CUSTOMER_ID"); // Get int directly
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return customerID;
        }
    }

    public int updateCustomerInDB(Customer customer, String nameToUpdate) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("UPDATE CUSTOMER SET CUSTOMERNAME = ?, CUSTOMERPHONE = ?, CUSTOMER_ADDRESS_LINE1 = ?, CUSTOMER_ADDRESS_LINE2 = ?, CUSTOMER_ADDRESS_CITY = ?, CUSTOMER_ADDRESS_STATE = ?, CUSTOMER_ADDRESS_ZIP = ?, customer_payment_card_number = ?, customer_payment_card_name = ?, customer_payment_card_expiry = ?, CUSTOMER_PAYMENT_CARD_CVV = ?, CARD_TYPE = ?  WHERE CUSTOMER_ID = ?");

            // Set values for update based on the customer object
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getCustomerAddress().getLine1Address());
            ps.setString(4, customer.getCustomerAddress().getLine2Address());
            ps.setString(5, customer.getCustomerAddress().getCity());
            ps.setString(6, customer.getCustomerAddress().getState());
            ps.setString(7, customer.getCustomerAddress().getZipCode());
            ps.setString(8, customer.getCustomerPayment().getCardNumber());
            ps.setString(9, customer.getCustomerPayment().getCardName());
            ps.setString(10, customer.getCustomerPayment().getExpDate());
            ps.setInt(11, customer.getCustomerPayment().getCvvCode());
            ps.setString(12, customer.getCustomerPayment().getCardType());

            // Specify the customer ID for update based on a search or selection criteria
            ps.setInt(13, getCustomerIDFromDBbyName(nameToUpdate));  // Update based on customer ID

            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
            return count;
        }
    }

    public int removeCustomerFromDB(String nameToDelete) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int rowsDeleted = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMERNAME = ?");

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

        CustomerDBManager cdm = new CustomerDBManager();




        //cdm.insertCustomersIntoDB(customerSet);


    }

}
