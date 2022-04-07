package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;


//this class is used for adding new customer and getting customer/all customers
public class CustomerService {


//    using singleton design pattern, so we can create only one instance of class ata a time
    private static final CustomerService SINGLETON = new CustomerService();
    private CustomerService(){};
    public static CustomerService getSingleton() {
        return SINGLETON;
    }

//   using Arraylist from java collections for storing customers
    ArrayList<Customer> customersList = new ArrayList<>();

//    method for adding new customer are using Customer class and adding it to customer list.
    public void addCustomer(String firstName, String lastName, String email){
        Customer customer = new Customer(firstName, lastName, email);
        customersList.add(customer);
    }

//    by this method we can search customer by using its registered email
    public Customer getCustomer(String customerEmail){
//        iterating on customer list i.e.  arraylist and matching email address with all customers email if
//        found we will simply return this
        for(Customer customer : customersList){
            if(customer.getEmail().equals(customerEmail)){
                return customer;

            }
        }
        return null;
    }
// this method will return all customers
    public ArrayList<Customer> getAllCustomers(){
        return customersList;
    }
}
