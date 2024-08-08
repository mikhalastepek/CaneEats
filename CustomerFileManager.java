package edu.bus.bte324.canehub.filemanagers;

import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.infoclasses.AddressInfo;
import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class CustomerFileManager {
    private final String CUSTOMER_FILE = "DataFiles/CUSTOMER_DATA.csv";

    public static void main(String[] args) throws Exception {
        CustomerFileManager cfm = new CustomerFileManager();
        CustomerDBManager cdm = new CustomerDBManager();
        cfm.getAllCustomersFromFile();

        Customer c = new Customer("ABCDEF", "30541202220");
        c.setCustomerAddress(new AddressInfo("Smart Street", "000", "New Line2", "MA", "Boston", "23434"));
        c.setCustomerPayment(new PaymentInfo("23423432423423", "Lokesh", "Master card", 999, "2024"));

        cfm.addCustomerToFile(c);

        HashSet<Customer> allCustomers =
                (cfm.getAllCustomersFromFile());

        HashSet<Customer> newList = cfm.removeCustomerByName(allCustomers, "Honey");

        cdm.insertCustomersIntoDB(cfm.getAllCustomersFromFile());

        cfm.writeCustomersToFile(newList);

        cfm.getAllCustomersFromFile();

    }

    public HashSet<Customer> removeCustomerByName(HashSet<Customer> originalSet, String custName) {
        for (Customer c : originalSet)
            if (c != null && c.getCustomerName().equalsIgnoreCase(custName)) {
                originalSet.remove(c);
                break;
            }

        return originalSet;
    }


    public int writeCustomersToFile(HashSet<Customer> customerSet) throws FileNotFoundException {
        int success = 0;
        File customerFile = new File(CUSTOMER_FILE);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(customerFile);
            PrintWriter pw = new PrintWriter(fos);
            for (Customer c : customerSet) {
                pw.println(c.toStringForFile());
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


    public int addCustomerToFile(Customer c) throws FileNotFoundException {
        int success = 0;
        File customerFile = new File(CUSTOMER_FILE);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(customerFile, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(c.toStringForFile());
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

    public HashSet<Customer> getAllCustomersFromFile() throws FileNotFoundException {
        HashSet<Customer> customerSet = new HashSet<Customer>();
        File inputFile = new File(CUSTOMER_FILE);
        FileInputStream fis = null;
        int recordCounter = 0;
        try {
            fis = new FileInputStream(inputFile);
            Scanner customerScanner = new Scanner(fis);


            while (customerScanner.hasNext()) {
                //System.out.println(customerScanner.nextLine());
                String lineData = customerScanner.nextLine();
                String[] custString = lineData.split(",");
                Customer c = new Customer(custString[0], custString[1]);
                c.setCustomerAddress(new AddressInfo(custString[2], custString[3], custString[4], custString[6], custString[5], custString[7]));
                c.setCustomerPayment(new PaymentInfo(custString[8], custString[9], custString[10], Integer.parseInt(custString[11].trim()), custString[12]));
                System.out.println(c);

                customerSet.add(c);

                recordCounter++;

            }

        } catch (FileNotFoundException fne) {
            throw fne;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Total Number of Customer Records:" + recordCounter);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return customerSet;
    }


}
