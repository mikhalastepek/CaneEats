package edu.bus.bte324.canehub.boundary;

import edu.bus.bte324.canehub.controller.CustomerInfoManager;
import edu.bus.bte324.canehub.controller.OrderInfoManager;
import edu.bus.bte324.canehub.controller.RestaurantInfoManager;

import java.util.Scanner;

public class CaneHubApplication {
    public static void main(String[] args) throws Exception {

        CustomerInfoManager cim = new CustomerInfoManager();
        RestaurantInfoManager rim = new RestaurantInfoManager();
        OrderInfoManager oim = new OrderInfoManager();
       // RestaurantInfoManager rim = new RestaurantInfoManager();
        boolean exitFlag = true;
        while (exitFlag)
        {
            System.out.println("Welcome to CaneHub Application");
        System.out.println("Choose from the following option");
        System.out.println();
        System.out.println("1. Customer Info Management");
        System.out.println("2. Restaurant Info Management");
        System.out.println("3. Order Management");
        System.out.println("4. Exit the System");
        System.out.println("Input your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                cim.manage();
                break;
            case 2:
                rim.manage();
                break;

            case 3:
                oim.manage();
                break;
            case 4:
                exitFlag = false;
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

            System.out.println("Thank you for using CaneHub - a copy of GrubHub.");
    }

    }
}
