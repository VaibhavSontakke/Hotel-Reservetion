package model;

import java.util.Date;
import java.util.Objects;

//this class handle the reservation of customer
public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

//    this will store the reservation information for specific reservation
    public Reservation(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


//    Adding getters for getting reservation information
    public Customer getCustomer() {
        return this.customer;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }


//    using toString method for getting result in proper format
    @Override
     public String toString(){
        return this.customer.getFirstName() +" "+ this.customer.getLastName() + " booked "+ this.room.getRoomNumber()+" room from " + this.checkInDate + " to " + this.checkOutDate;
    }

    //    Reference : https://www.baeldung.com/java-equals-hashcode-contracts
    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Reservation)){
            return false;
        }

        Reservation other = (Reservation) obj;
        boolean customerEquals = (this.customer == null && other.customer == null)||
                (this.customer != null && this.customer.equals(other.customer));
        boolean roomEquals = (this.room== null && other.room == null)||
                (this.room != null && this.room.equals(other.room));
        boolean checkInDateEquals = (this.checkInDate == null && other.checkInDate == null)||
                (this.checkInDate != null && this.checkInDate.equals(other.checkInDate));
        boolean checkOutDateEquals = (this.checkOutDate == null && other.checkOutDate == null)||
                (this.checkOutDate != null && this.checkOutDate.equals(other.checkOutDate));

        return customerEquals && roomEquals && checkInDateEquals && checkOutDateEquals;
    }

    @Override
    public int hashCode(){
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }


}
