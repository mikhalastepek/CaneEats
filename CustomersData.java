package edu.bus.bte324.canehub.appdata;

import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.entities.Customer;

import java.util.HashSet;

public class CustomersData {
    public HashSet<Customer> customerSet = new HashSet<>();
    private CustomerDBManager cdm = new CustomerDBManager();

    public boolean addCustomer(Customer c)
    {
        boolean status = customerSet.add(c);
        try {
            cdm.insertSingleCustomerIntoDB(c);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;

    }

    public boolean removeCustomer(Customer c)
    {
         return customerSet.remove(c);
    }

    public boolean updateCustomer(Customer oldValue, Customer newValue)
    {
        customerSet.remove(oldValue);
        return customerSet.add(newValue);
    }

    public void displayAll()
    {
        System.out.println("Printing all the customers here:");

        try {
            for(Customer c:  cdm.getAllCustomersFromDB())
            {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
