package edu.bus.bte324.canehub.appdata;

import edu.bus.bte324.canehub.dbmanagers.MenuItemDBManager;
import edu.bus.bte324.canehub.entities.MenuItem;

import java.util.HashSet;

public class MenuItemData {
    public HashSet<MenuItem> menuSet = new HashSet<>();
    private MenuItemDBManager mdm = new MenuItemDBManager();

    public boolean addMenuItem(MenuItem m)
    {
        boolean status = menuSet.add(m);
        try {
            mdm.insertSingleMenuItemIntoDB(m);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;

    }

    public boolean removeMenuItem(MenuItem m)
    {
         return menuSet.remove(m);
    }

    public boolean updateMenuItem(MenuItem oldValue, MenuItem newValue)
    {
        menuSet.remove(oldValue);
        return menuSet.add(newValue);
    }

    public void displayAll()
    {
        System.out.println("Printing all the menu items here:");

        try {
            for(MenuItem m:  mdm.getAllMenuItemsFromDB())
            {
                System.out.println(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
