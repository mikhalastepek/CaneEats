package edu.bus.bte324.canehub.controller;

import edu.bus.bte324.canehub.appdata.CustomersData;
import edu.bus.bte324.canehub.appdata.OrdersData;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;
import edu.bus.bte324.canehub.dbmanagers.OrderDBManager;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Order;
import edu.bus.bte324.canehub.entities.Restaurant;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

import static edu.bus.bte324.canehub.utilities.AddressInputUtility.getAddressInfoFromConsole;
import static edu.bus.bte324.canehub.utilities.PaymentInputUtility.getPaymentInfoFromConsole;

public class OrderInfoManager {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");



    private OrdersData od = new OrdersData();
    private OrderDBManager odm = new OrderDBManager();
    private RestaurantDBManager rbm = new RestaurantDBManager();

    private OrderItemInfoManager oiim = new OrderItemInfoManager();

    public void manage() throws Exception {
        System.out.println();
        System.out.println("Order Management Page: Type 1 or 2.");
        System.out.println("1. Start new order");
        System.out.println("2. View past orders");

        Scanner orderScanner = new Scanner(System.in);
        int choice = orderScanner.nextInt();
        boolean found = false;
        HashSet<Order> orderSet;

        switch(choice)
        {
            case 1:
                System.out.println();
                System.out.println("Starting a new Order");
                System.out.println("Input Customer Name :");
                orderScanner.nextLine();
                String customerName = orderScanner.nextLine();
                System.out.println("Here are your restaurant options:");
                HashSet<Restaurant> restSet = rbm.getAllRestaurantsFromDB();

                for(Restaurant r : restSet){
                    r.toString();
                }
                System.out.println("Enter the name of the restaurant from which you'd like to order");
                String restaurantName = orderScanner.nextLine();

                Date orderDate = new Date();  // Get the current date
                String formattedDate = simpleDateFormat.format(orderDate);
                System.out.println(formattedDate);

                int customerID = odm.getCustomerIDFromDBbyName(customerName);
                int restaurantID = odm.getRestaurantIDFromDBbyName(restaurantName);

                Order o = new Order(customerID,restaurantID);
                o.setOrderID(odm.getOrderIDFromDBbyRestaurantAndCustomer(o.getRestaurantID(),o.getCustomerID()));

                Date today = new Date();
                o.setOrderDate(String.valueOf(orderDate));

                oiim.manage(o);

                //add menu items to order
                odm.insertSingleOrderIntoDB(o);
                System.out.println("New Order Created Successfully");
                System.out.println();
                break;

            case 2:
                System.out.println();
                System.out.println("View past orders :");
                System.out.println();
                System.out.println("Input customer Name :");
                orderScanner.nextLine();
                String pastOrderCustomerName = orderScanner.nextLine();
                System.out.println(pastOrderCustomerName);
                int pastOrdersCustomerID = odm.getCustomerIDFromDBbyName(pastOrderCustomerName);
                if(pastOrdersCustomerID!=-1){found = true;}

                if(found) {
                    orderSet = odm.getOrdersFromDBbyCustomerID(pastOrdersCustomerID);
                    for(Order order: orderSet){
                        System.out.println(order);
                    }
                }

                else {
                    System.out.println("Customer not found!");
                    System.out.println();
                    break;
                }



                break;
            default: break;
        }
    }


    public static void main(String[] args) throws Exception{

        OrderInfoManager oim = new OrderInfoManager();
        Date date = new Date();  // Get the current date
        String formattedDate = oim.simpleDateFormat.format(date);
        System.out.println(formattedDate);  // Output: 2024-05-02 (assuming today is May 2nd)



    }
}

