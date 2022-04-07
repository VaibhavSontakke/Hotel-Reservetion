package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Date;

public class HotelResource {
//    making singleton instance and constructor private so only one instance is return at a time
    private static final HotelResource SINGLETON = new HotelResource();
    private HotelResource(){};
    public static HotelResource getSingleton(){
        return SINGLETON;
    }

//    for getting customer using email
    public Customer getCustomer(String email){
        return CustomerService.getSingleton().getCustomer(email);
    }

//    this will create new customer
    public void createCustomer(String email, String firstName, String lastName){
        CustomerService.getSingleton().addCustomer(email,firstName,lastName);
    }

//    this will return room from reservations
    public IRoom getRoom(String roomNumber){
        return ReservationService.getSingleton().getARoom(roomNumber);
    }

//    it will check is room number is unique or not
    public boolean checkDuplicateNumber(String roomNumber){
        return ReservationService.getSingleton().checkDuplicateNumber(roomNumber);
    }

//    this will book new room
    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getSingleton().reserveRoom(customer, room, checkInDate, checkOutDate);
    }

//   this will return all reservations done by single customer
    public ArrayList<Reservation> getCustomerReservations(String customerEmail){
        return ReservationService.getSingleton().getCustomersReservation(getCustomer(customerEmail));
    }

//  this will find room which is available for given dates and return it
    public ArrayList<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.getSingleton().findRooms(checkIn, checkOut);
    }

//    this will return all reservations
    public ArrayList<Reservation> getAllReservations(){
        return ReservationService.getSingleton().getReservationList();
    }

    public boolean isReservered(IRoom room, Date checkIn, Date checkOut){
        return ReservationService.getSingleton().isReserved(room,checkIn, checkOut);
    }
}
