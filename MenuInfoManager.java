package edu.bus.bte324.canehub.controller;

import edu.bus.bte324.canehub.appdata.MenuItemData;
import edu.bus.bte324.canehub.dbmanagers.MenuItemDBManager;
import edu.bus.bte324.canehub.entities.MenuItem;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;


import java.util.HashSet;
import java.util.Scanner;


public class MenuInfoManager {
    private MenuItemData md = new MenuItemData();
    private MenuItemDBManager mdm = new MenuItemDBManager();
    private RestaurantDBManager rdm = new RestaurantDBManager();

    public void manage() throws Exception {
        System.out.println();
        System.out.println("Menu Item Management Page");
        System.out.println("1. Add Menu Item");
        System.out.println("2. Edit Menu Item Info");
        System.out.println("3. Delete Menu Item");
        System.out.println("4. Print All Menu Items");

        Scanner menuScanner = new Scanner(System.in);
        int choice = menuScanner.nextInt();
        boolean found = false;
        HashSet<MenuItem> menuSet;

        switch(choice)
        {
            case 1:
                System.out.println();
                System.out.println("Creating a new Menu Item");
                System.out.println("What is the restaurant name for this item?");
                String restName = menuScanner.nextLine();
                System.out.println("Input Menu Item Name :");
                menuScanner.nextLine();
                String menuItemName = menuScanner.nextLine();
                System.out.println("Input Menu Item Price :");
                double menuItemPrice = menuScanner.nextDouble();
                System.out.println("Input the Menu Item Description");
                String mDescription = menuScanner.nextLine();

                int restID;
                int rID = rdm.getRestaurantIDFromDBbyName(restName);

                MenuItem m = new MenuItem(menuItemName,menuItemPrice, rID);

                // AddressInputUtility au = new AddressInputUtility()
                m.setMenuItemDescription(mDescription);

                mdm.insertSingleMenuItemIntoDB(m);
                System.out.println("New Menu Item [" +m.getMenuItemName()+"] Created Successfully : "+m.toStringForFile());
                System.out.println();
                break;

            case 2:
                System.out.println();
                System.out.println("Editing customer info :");
                System.out.println();
                System.out.println("Input customer Name :");
                menuScanner.nextLine();
                String nameToUpdate = menuScanner.nextLine();
                System.out.println(nameToUpdate);
                menuSet = mdm.getMenuItemsFromDBbyName(nameToUpdate);


                for(MenuItem item:  menuSet)
                {
                    if(item.getMenuItemName().equals(nameToUpdate)){
                        found = true;
                        System.out.println("What would you like to update? name, address, phone, or payment?");
                        String itemToUpdate = menuScanner.nextLine();

                        switch (itemToUpdate) {
                            case "name" -> {
                                System.out.println("What is the new name?");
                                String newName = menuScanner.nextLine();
                                item.setMenuItemName(newName);
                                System.out.println(item.getMenuItemName());
                                System.out.println("Successfully changed name to " + newName + ".");
                            }
                            case "price" -> {
                                System.out.println("What is the new price?");
                                double newPrice = menuScanner.nextDouble();
                                item.setPrice(newPrice);
                                System.out.println("Successfully changed number to " + newPrice);
                            }
                            case "description" -> {
                                System.out.println("What is the new description?");
                                String newDescription = menuScanner.nextLine();
                                item.setMenuItemDescription(newDescription);
                                System.out.println("Successfully changed address.");

                            }

                        }
                        mdm.insertSingleMenuItemIntoDB(item);
                        System.out.println("Successfully edited menu item!");
                    }
                }
                if(!found){
                    System.out.println("Menu item not found!");
                    System.out.println();
                    break;
                }



                break;

            case 3:
                System.out.println("Deleting customer :");
                System.out.println("Input customer Name :");
                menuScanner.nextLine();
                String nameToDelete = menuScanner.nextLine();
                System.out.println(nameToDelete);
                menuSet = mdm.getMenuItemsFromDBbyName(nameToDelete);

                for(MenuItem item: menuSet) {
                    if(item.getMenuItemName().equals(nameToDelete)){
                        found = true;
                        md.removeMenuItem(item);
                        System.out.println("Successfully deleted " + nameToDelete + ".");
                    }
                    if(!found){
                        System.out.println("Customer not found!");
                        System.out.println();
                        break;
                    }
                }

                break;


            case 4: md.displayAll();
            default: break;
        }
    }
}
