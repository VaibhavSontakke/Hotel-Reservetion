package service;

import model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//this class is contains all reservation services like reserve room, add room etc.
public class ReservationService {

//    making singleton instance and constructor  private so only one instance is return at a time
    private static final ReservationService SINGLETON = new ReservationService();
    private ReservationService(){};
    public static ReservationService getSingleton() {
        return SINGLETON;
    }

//    using arraylist from java collections for storing rooms and reservation
    ArrayList<IRoom> roomList = new ArrayList<>();
    ArrayList<Reservation> reservationList = new ArrayList<>();

//    this method wil take input from admin and create new room using room class
//    after creating new room we will add it to room list
    public void addRoom(String roomNumber, Double price, RoomType enumeration){
//      create and add new room
        Room room = new Room(roomNumber, price, enumeration);
        roomList.add(room);
    }

    //        this will handle duplicate room number case
    public boolean checkDuplicateNumber(String roomNumber){
        for(IRoom room : roomList) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return true;
            }
        }
        return false;
    }

//    by this method we will search any room by room number
    public IRoom getARoom(String roomNumber){
        for(IRoom room : roomList){
            if(room.getRoomNumber().equals(roomNumber)){
                return room;
            }
        }
        return null;
    }
//  this will return all rooms
    public ArrayList<IRoom> getAllRooms(){
        return roomList;
    }

//    this method will reserve room for customer and new reservation to reservation list
    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        return reservation;
    }

//    this method is search room which is available for given dates
    public ArrayList<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        ArrayList<IRoom> availableRooms = new ArrayList<>();

//    we will iterate over all rooms and check is it reserved or not
        for(IRoom room : roomList){
            // this helper function will search rooms in reservation list
            // if rooms found for desired dates we will add it to available room list
            boolean result = isReserved(room, checkInDate, checkOutDate);
                if(!result){
                    availableRooms.add(room);
                }

            }

        return availableRooms;
    }


//helper function for findroom method
    public boolean isReserved(IRoom room, Date checKInDate, Date checkOutDate){
//        if reservation list is empty then all rooms are available
        if(reservationList.isEmpty()){
            return false;
        }
//        else we will iterate over reservation list and compare check in and check out date
        for (Reservation reservation : reservationList){
//         if dates are not overlapping then this room is available
            if(reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())){
                boolean checkOverLap = (checKInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate()));
                if(!checkOverLap){
                    return true;
                }
            }
        }
        return false;
    }

//    this will search the all reservation of a customer
    public ArrayList<Reservation> getCustomersReservation(Customer customer){
        ArrayList<Reservation> customerReservationList = new ArrayList<>();

        for(Reservation reservation : reservationList){
            if(reservation.getCustomer().equals(customer)){
                customerReservationList.add(reservation);
            }
        }
        return customerReservationList;
    }

    public ArrayList<Reservation> getReservationList(){
        return reservationList;
    }


//    this will print all reservation for admin
    public void printAllReservation(){
        if(reservationList.isEmpty()){
            System.out.println("No reservations available!");
        }else{
            for(Reservation reservation : reservationList){
                System.out.println(reservation);
            }
        }
    }
}
