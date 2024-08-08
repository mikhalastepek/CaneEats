package edu.bus.bte324.canehub.entities;
import java.util.Date;

public class Order {
    private int customerID;
    private int restaurantID;
    private String orderDate;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    private int orderID;


    public Order(int customerID, int restaurantID) {
        this.customerID = customerID;
        this.restaurantID = restaurantID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    @Override
    public String toString() {
        return "Order{" +
                "customerID=" + customerID +
                ", restaurantID=" + restaurantID +
                ", orderDate='" + orderDate + '\'' +
                ", orderID=" + orderID +
                '}';
    }
}
