package edu.bus.bte324.canehub.appdata;

import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Restaurant;

import java.util.HashSet;

public class RestaurantData {
    public HashSet<Restaurant> restaurantSet = new HashSet<Restaurant>();
    private RestaurantDBManager rdm = new RestaurantDBManager();

    public boolean addRestaurant(Restaurant r)
    {
        boolean status = restaurantSet.add(r);
        try {
            rdm.insertSingleRestaurantIntoDB(r);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;

    }

    public boolean removeRestaurant(Restaurant r)
    {
         return restaurantSet.remove(r);
    }

    public boolean updateRestaurant(Restaurant oldValue, Restaurant newValue)
    {
        restaurantSet.remove(oldValue);
        return restaurantSet.add(newValue);
    }

    public void displayAll()
    {
        System.out.println("Printing all the restaurants here:");

        try {
            for(Restaurant r:  rdm.getAllRestaurantsFromDB())
            {
                System.out.println(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
