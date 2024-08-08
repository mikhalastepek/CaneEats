package edu.bus.bte324.canehub.controller;

import edu.bus.bte324.canehub.appdata.CustomersData;
import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.entities.Customer;

import java.util.HashSet;
import java.util.Scanner;
import static edu.bus.bte324.canehub.utilities.AddressInputUtility.getAddressInfoFromConsole;
import static edu.bus.bte324.canehub.utilities.PaymentInputUtility.getPaymentInfoFromConsole;

public class CustomerInfoManager {

    private CustomersData cd = new CustomersData();
    private CustomerDBManager cdm = new CustomerDBManager();

    public void manage() throws Exception {
        System.out.println();
        System.out.println("Customer Management Page");
        System.out.println("1. Add Customer");
        System.out.println("2. Edit Customer Info");
        System.out.println("3. Delete Customer");
        System.out.println("4. Print All Customers");

        Scanner custScanner = new Scanner(System.in);
        int choice = custScanner.nextInt();
        boolean found = false;
        HashSet<Customer> customerSet;
 
        switch(choice)
        {
            case 1:
                System.out.println();
                System.out.println("Creating a new Customer");
                System.out.println("Input Customer Name :");
                custScanner.nextLine();
                String custName = custScanner.nextLine();
                System.out.print("Input Customer Phone :");
                String custPhone = custScanner.nextLine();

                Customer c = new Customer(custName,custPhone);

               // AddressInputUtility au = new AddressInputUtility()
                c.setCustomerAddress(getAddressInfoFromConsole());

                c.setCustomerPayment(getPaymentInfoFromConsole());
                cdm.insertSingleCustomerIntoDB(c);
                System.out.println("New Customer [" +c.getCustomerName()+"] Created Successfully : "+c.toStringForFile());
                System.out.println();
                break;

            case 2:
                System.out.println();
                System.out.println("Editing customer info :");
                System.out.println();
                System.out.println("Input customer Name :");
                custScanner.nextLine();
                String nameToUpdate = custScanner.nextLine();
                System.out.println(nameToUpdate);
                customerSet = cdm.getCustomersFromDBbyName(nameToUpdate);


                for(Customer cust:  customerSet)
                {
                    if(cust.getCustomerName().equals(nameToUpdate)){
                        found = true;
                        System.out.println("What would you like to update? name, address, phone, or payment?");
                        String itemToUpdate = custScanner.nextLine();

                        switch (itemToUpdate) {
                            case "name" -> {
                                System.out.println("What is the new name?");
                                String newName = custScanner.nextLine();
                                cust.setCustomerName(newName);
                                System.out.println(cust.getCustomerName());
                                System.out.println("Successfully changed name to " + newName + ".");
                            }
                            case "phone" -> {
                                System.out.println("What is the new number?");
                                String newNumber = custScanner.nextLine();
                                cust.setPhoneNumber(newNumber);
                                System.out.println("Successfully changed number to " + newNumber);
                            }
                            case "address" -> {
                                cust.setCustomerAddress(getAddressInfoFromConsole());
                                System.out.println("Successfully changed address.");

                            }
                            case "payment" -> {
                                cust.setCustomerPayment(getPaymentInfoFromConsole());
                                System.out.println("Successfully changed payment info");
                            }

                        }
                        cdm.updateCustomerInDB(cust, nameToUpdate);
                        System.out.println("Successfully edited customer!");
                    }
                    }
                if(!found){
                    System.out.println("Customer not found!");
                    System.out.println();
                    break;
                }



                break;

            case 3:
                System.out.println("Deleting customer :");
                System.out.println("Input customer Name :");
                custScanner.nextLine();
                String nameToDelete = custScanner.nextLine();
                System.out.println(nameToDelete);
                customerSet = cdm.getCustomersFromDBbyName(nameToDelete);

                for(Customer cust: customerSet) {
                    if(cust.getCustomerName().equals(nameToDelete)){
                        found = true;
                        cd.removeCustomer(cust);
                        cdm.removeCustomerFromDB(nameToDelete);
                        System.out.println("Successfully deleted " + nameToDelete + ".");
                    }
                    if(!found){
                        System.out.println("Customer not found!");
                        System.out.println();
                        break;
                    }
                }

                break;


            case 4: cd.displayAll();
            default: break;
        }
    }
}
