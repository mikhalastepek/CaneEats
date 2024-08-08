package edu.bus.bte324.canehub.controller;

import edu.bus.bte324.canehub.appdata.RestaurantData;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;
import edu.bus.bte324.canehub.entities.Restaurant;

import java.util.HashSet;
import java.util.Scanner;

public class RestaurantInfoManager {

    private RestaurantData rd = new RestaurantData();
    private RestaurantDBManager rdm = new RestaurantDBManager();

    public void manage() throws Exception {
        System.out.println("Restaurant Management Page");
        System.out.println("1. Add Restaurant");
        System.out.println("2. Edit Restaurant Info");
        System.out.println("3. Delete Restaurant");
        System.out.println("4. Print All Restaurant");

        boolean found = false;
        Scanner restScanner = new Scanner(System.in);
        int choice = restScanner.nextInt();

        HashSet<Restaurant> restaurantSet;
 
        switch(choice)
        {
            case 1:
                System.out.println("Creating a new Restaurant");
                Scanner rInfoScanner = new Scanner(System.in);
                System.out.print("Input Restaurant Name :");
                String rName = rInfoScanner.nextLine();
                System.out.print("Input Restaurant Cuisine :");
                String rCuisine = rInfoScanner.nextLine();

                Restaurant r = new Restaurant(rName,rCuisine);

                System.out.println("Input Restaurant Location :");
                r.setRestaurantLocation(rInfoScanner.nextLine());


                System.out.println("New Restaurant Created Successfully : "+r.toStringForFile());
                rd.addRestaurant(r);
                break;

//            case 2:
//                System.out.println("Editing restaurant info :");
//                Scanner restInfoScanner = new Scanner(System.in);
//                System.out.println("Input Restaurant Name :");
//                String nameToUpdate = restInfoScanner.nextLine();
//                restaurantSet = rdm.getRestaurantsFromDBbyName(nameToUpdate);
//                System.out.println(restaurantSet);
//
//                for (Restaurant rest : restaurantSet) {
//                    System.out.println(rest.getRestaurantName());
//                    if(rest.getRestaurantName().equals(nameToUpdate)){
//                        System.out.println("What would you like to update? name, cuisine, or location?");
//                        String itemToUpdate = restInfoScanner.nextLine();
//                        System.out.println("What is the new value?");
//                        String newValue = restInfoScanner.nextLine();
//
//                        switch (itemToUpdate) {
//                            case "name" -> {
//                                Restaurant updatedRest = new Restaurant(newValue, rest.getCuisine());
//                                updatedRest.setRestaurantLocation(rest.getRestaurantLocation());
//                                rd.updateRestaurant(rest, updatedRest);
//                                rdm.updateRestaurantInDB(updatedRest,nameToUpdate);
//                            }
//                            case "cuisine" -> {
//                                Restaurant updatedRest = new Restaurant(nameToUpdate, newValue);
//                                updatedRest.setCuisine(rest.getCuisine());
//                                rd.updateRestaurant(rest, updatedRest);
//                                rdm.updateRestaurantInDB(updatedRest,nameToUpdate);
//                            }
//                            case "location" -> {
//                                Restaurant updatedRest = new Restaurant(nameToUpdate, rest.getCuisine());
//                                updatedRest.setRestaurantLocation(newValue);
//                                rd.updateRestaurant(rest, updatedRest);
//                                rdm.updateRestaurantInDB(updatedRest,nameToUpdate);
//                            }
//
//                        }
//                        System.out.println("Successfully edited restaurant");
//
//
//
//                    }
//
//                }
//
//
//
//                break;
            case 2:
                System.out.println();
                System.out.println("Editing restaurant info :");
                System.out.println();
                System.out.println("Input restaurant name :");
                restScanner.nextLine();
                String nameToUpdate = restScanner.nextLine();
                System.out.println(nameToUpdate);
                restaurantSet = rdm.getRestaurantsFromDBbyName(nameToUpdate);


                for(Restaurant rest:  restaurantSet)
                {
                    if(rest.getRestaurantName().equals(nameToUpdate)){
                        found = true;
                        System.out.println("What would you like to update? name, cuisine, or location?");
                        String itemToUpdate = restScanner.nextLine();

                        switch (itemToUpdate) {
                            case "name" -> {
                                System.out.println("What is the new name?");
                                String newName = restScanner.nextLine();
                                rest.setRestaurantName(newName);
                                rdm.updateRestaurantInDB(rest, nameToUpdate);
                                System.out.println(rest.getRestaurantName());
                                System.out.println("Successfully changed name to " + newName);
                            }
                            case "cuisine" -> {
                                System.out.println("What is the new cuisine?");
                                String newCuisine = restScanner.nextLine();
                                rest.setCuisine(newCuisine);
                                System.out.println(rest.getCuisine());
                                rdm.updateRestaurantInDB(rest, nameToUpdate);
                                System.out.println(rdm.getRestaurantsFromDBbyName(nameToUpdate));
                                System.out.println("Successfully changed cuisine to " + newCuisine);
                            }
                            case "location" -> {
                                System.out.println("What is the new location?");
                                String newLocation = restScanner.nextLine();
                                rest.setRestaurantLocation(newLocation);
                                rdm.updateRestaurantInDB(rest, nameToUpdate);
                                System.out.println(rest.getRestaurantLocation());
                                System.out.println("Successfully changed location to " + newLocation);

                            }

                        }
                        System.out.println(rest.getCuisine());
                        System.out.println("Successfully edited customer!");
                    }
                }
                if(!found){
                    System.out.println("Restaurant not found!");
                    System.out.println();
                    break;
                }



                break;

            case 3:
                System.out.println("Deleting restaurant :");
                Scanner resInfoScanner = new Scanner(System.in);
                System.out.println("Input Restaurant Name :");
                String nameToDelete = resInfoScanner.nextLine();
                rdm.removeRestaurantFromDB(nameToDelete);
                System.out.println("Restaurant deleted.");


                break;
                
            case 4: rd.displayAll();
            default: break;
        }
    }

}
