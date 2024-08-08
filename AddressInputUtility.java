package edu.bus.bte324.canehub.utilities;

import edu.bus.bte324.canehub.infoclasses.AddressInfo;

import java.util.Scanner;

public class AddressInputUtility {
    
    
    //This method is static because we donot require to instantiate AddressInputUtility to invoke this method.
    public static AddressInfo getAddressInfoFromConsole()
    {
        System.out.println("Input the address");
        Scanner addrInfoScanner = new Scanner(System.in);
        System.out.print("Input Street Address :");
        String street = addrInfoScanner.nextLine();
        System.out.print("Input Line1 Address :");
        String line1 = addrInfoScanner.nextLine();
        System.out.print("Input Line2 Address :");
        String line2 = addrInfoScanner.nextLine();
        System.out.print("Input City  :");
        String city = addrInfoScanner.nextLine();
        System.out.print("Input State  :");
        String state = addrInfoScanner.nextLine();
        System.out.print("Input Zip  :");
        String zip = addrInfoScanner.nextLine();


        AddressInfo addressInfo = new AddressInfo(street,line1,line2,state,city,zip);
        return addressInfo;
    }
}
