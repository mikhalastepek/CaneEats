package edu.bus.bte324.canehub.entities;
import edu.bus.bte324.canehub.entities.Restaurant;
public class MenuItem {
    private String menuItemName;
    private String menuItemDescription;

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        restaurantID = restaurantID;
    }

    private int restaurantID;

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemName='" + menuItemName + '\'' +
                ", menuItemDescription='" + menuItemDescription + '\'' +
                ", restaurantID=" + restaurantID +
                ", price=" + price +
                '}';
    }

    public MenuItem(String name, double price, int restaurantID) {
        this.menuItemName = name;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;


    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String name) {
        this.menuItemName = name;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String description) {
        this.menuItemDescription = description;
    }


    public String toStringForFile() {
        return menuItemName + ", " + price + ", " + menuItemDescription;
    }
}



