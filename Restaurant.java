package edu.bus.bte324.canehub.entities;

import edu.bus.bte324.canehub.infoclasses.AddressInfo;

public class Restaurant
{
    private String restaurantName;
    private String restaurantLocation;
    private String cuisine;

    public Restaurant(String restaurantName, String cuisine) {
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String toString() {
        return "Restaurant{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantLocation=" + restaurantLocation +
                ", cuisine='" + cuisine + '\'' +
                '}';
    }
    public String toStringForFile() {
        return restaurantName + "," + cuisine + " , "+  restaurantLocation;
    }


    //TODO Generate getters, setters, toString method, constructors.
}