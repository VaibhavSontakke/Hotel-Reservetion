package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class AdminResource {
    //    making singleton instance and constructor private so only one instance is return at a time
    private static final AdminResource SINGLETON = new AdminResource();
    private AdminResource(){};
    public static AdminResource getSingleton(){
        return SINGLETON;
    }

//    this method search customer by using email
    public Customer getCustomer(String email){
        return CustomerService.getSingleton().getCustomer(email);
    }
//  this will add new room
    public void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            ReservationService.getSingleton().addRoom(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType());
        }
    }
//  this will return all rooms
    public ArrayList<IRoom> getAllRooms(){
        return ReservationService.getSingleton().getAllRooms();
    }

//    this will return all customers
    public ArrayList<Customer> getAllCustomers(){
        return CustomerService.getSingleton().getAllCustomers();
    }

//    this for displaying all reservations
    public void displayAllReservations(){
        ReservationService.getSingleton().printAllReservation();
    }
}
