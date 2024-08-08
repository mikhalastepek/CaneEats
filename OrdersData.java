package edu.bus.bte324.canehub.appdata;

import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.dbmanagers.OrderDBManager;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Order;

import java.util.HashSet;

public class OrdersData {
    public HashSet<Order> orderSet = new HashSet<>();
    private OrderDBManager odm = new OrderDBManager();

    public boolean addOrder(Order o)
    {
        boolean status = orderSet.add(o);
        try {
            odm.insertSingleOrderIntoDB(o);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;

    }


    public void displayOrdersByCustomerID(int cID)
    {
        System.out.println("Printing all the orders here:");

        try {
            for(Order o:  odm.getOrdersFromDBbyCustomerID(cID))
            {
                System.out.println(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
