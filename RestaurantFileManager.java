package edu.bus.bte324.canehub.filemanagers;

import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Restaurant;
import edu.bus.bte324.canehub.infoclasses.AddressInfo;
import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class RestaurantFileManager {
    private final String RESTAURANT_FILE = "DataFiles/RESTAURANT_DATA.csv";

    public static void main(String[] args) throws Exception {
        RestaurantFileManager rfm = new RestaurantFileManager();
        rfm.getAllRestaurantsFromFile();

        Restaurant r = new Restaurant("ABCDEF", "30541202220");
        r.setRestaurantLocation("Coral Gables");

        rfm.addRestaurantToFile(r);

        HashSet<Restaurant> allRestaurants =
                (rfm.getAllRestaurantsFromFile());

        HashSet<Restaurant> newList = rfm.removeRestaurantByName(allRestaurants, "Honey");

        rfm.writeRestaurantsToFile(newList);

        rfm.getAllRestaurantsFromFile();

    }

    public HashSet<Restaurant> removeRestaurantByName(HashSet<Restaurant> originalSet, String rName) {
        for (Restaurant r : originalSet)
            if (r != null && r.getRestaurantName().equalsIgnoreCase(rName)) {
                originalSet.remove(r);
                break;
            }

        return originalSet;
    }


    public int writeRestaurantsToFile(HashSet<Restaurant> restaurantSet) throws FileNotFoundException {
        int success = 0;
        File restaurantFile = new File(RESTAURANT_FILE);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(restaurantFile);
            PrintWriter pw = new PrintWriter(fos);
            for (Restaurant r : restaurantSet) {
                pw.println(r.toStringForFile());
                pw.flush();
                success++;
            }
        } catch (FileNotFoundException fne) {
            throw fne;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("No of records created " + success);
            return success;
        }

    }


    public int addRestaurantToFile(Restaurant r) throws FileNotFoundException {
        int success = 0;
        File restaurantFile = new File(RESTAURANT_FILE);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(restaurantFile, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(r.toStringForFile());
            pw.flush();
            success = 1;
        } catch (FileNotFoundException fne) {
            throw fne;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return success;
        }

    }

    public HashSet<Restaurant> getAllRestaurantsFromFile() throws FileNotFoundException {
        HashSet<Restaurant> restaurantSet = new HashSet<Restaurant>();
        File inputFile = new File(RESTAURANT_FILE);
        FileInputStream fis = null;
        int recordCounter = 0;
        try {
            fis = new FileInputStream(inputFile);
            Scanner restaurantScanner = new Scanner(fis);


            while (restaurantScanner.hasNext()) {
                //System.out.println(customerScanner.nextLine());
                String lineData = restaurantScanner.nextLine();
                String[] rString = lineData.split(",");
                Restaurant r = new Restaurant(rString[0], rString[1]);
                r.setRestaurantLocation(rString[2]);
                System.out.println(r);

                restaurantSet.add(r);

                recordCounter++;

            }

        } catch (FileNotFoundException fne) {
            throw fne;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Total Number of Restaurant Records:" + recordCounter);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return restaurantSet;
    }


}
