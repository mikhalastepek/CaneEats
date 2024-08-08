package edu.bus.bte324.canehub.entities;

public class OrderItem {
    private int orderID;

    public int getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(int menuItemID) {
        this.menuItemID = menuItemID;
    }

    private int menuItemID;
    private int itemQuantity;

    public OrderItem(int orderID, int menuItemID, int itemQuantity) {
        this.orderID = orderID;
        this.menuItemID = menuItemID;
        this.itemQuantity = itemQuantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
