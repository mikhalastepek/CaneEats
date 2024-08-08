package edu.bus.bte324.canehub.controller;

import edu.bus.bte324.canehub.appdata.CustomersData;
import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.dbmanagers.MenuItemDBManager;
import edu.bus.bte324.canehub.dbmanagers.OrderItemDBManager;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Order;
import edu.bus.bte324.canehub.entities.OrderItem;
import edu.bus.bte324.canehub.entities.MenuItem;
import edu.bus.bte324.canehub.entities.Restaurant;

import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;

import static edu.bus.bte324.canehub.utilities.AddressInputUtility.getAddressInfoFromConsole;
import static edu.bus.bte324.canehub.utilities.PaymentInputUtility.getPaymentInfoFromConsole;

public class OrderItemInfoManager {

    private CustomersData cd = new CustomersData();
    private CustomerDBManager cdm = new CustomerDBManager();

    private MenuItemDBManager mibm = new MenuItemDBManager();



    public void manage(Order o) throws Exception {
        Scanner orderItemScanner = new Scanner(System.in);
        OrderItemDBManager oidb  = new OrderItemDBManager();
        MenuItemDBManager midm = new MenuItemDBManager();

        System.out.println();
        System.out.println("OrderItem Management Page");
        System.out.println("Here are the menu items offered by the restaurant:");
        int keepOrdering = 1;
        while( keepOrdering !=0) {
        HashSet<MenuItem> menuItemSet = mibm.getMenuItemsByRestaurantFromDB(o.getRestaurantID());

        for(MenuItem m : menuItemSet){
            m.toString();
        }

            System.out.println("Add OrderItem: Enter the name of the item you'd like to order");
            String menuItemName = orderItemScanner.nextLine();
            System.out.println("Enter the quantity for this item:");
            int quantity = orderItemScanner.nextInt();
            int menuItemID = midm.getMenuItemIDFromDBbyRestaurantAndName(o.getRestaurantID(),menuItemName);
            OrderItem oi = new OrderItem(o.getOrderID(), menuItemID, quantity);
            oidb.insertSingleOrderItemIntoDB(oi);
            System.out.println("Enter 0 to complete your order, or 1 to add more items.");
            keepOrdering = orderItemScanner.nextInt();
            orderItemScanner.nextLine();
        }

    }
}

