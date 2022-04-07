package model;

import java.util.Objects;

//this class will store the information of specific room and implements the IRoom interface
public class Room implements IRoom{

    private String roomNumber;
    private Double price;
    private RoomType enumeration;


    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

//    Adding getters and setters

    public String getRoomNumber(){
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice(){
        return this.price;
    }

    public void setRoomNumber(double price){
        this.price = price;
    }
    public model.RoomType getRoomType(){
        return this.enumeration;
    }

    public void setRoomType(RoomType roomType){
        this.enumeration = roomType;
    }

    public boolean isFree(){
        return false;
    }

    @Override
//    using toString method for getting result in proper format
    public String toString(){
        return  " room "+ roomNumber +" "+ enumeration + " room and its cost is " + price + " per night";
    }

    //    Reference : https://www.baeldung.com/java-equals-hashcode-contracts
    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Room)){
            return false;
        }

        Room other = (Room) obj;
        boolean roomNumberEquals = (this.roomNumber == null && other.roomNumber == null)||
                (this.roomNumber != null && this.roomNumber.equals(other.roomNumber));
        boolean priceEquals = (this.price== null && other.price == null)||
                (this.price != null && this.price.equals(other.price));
        boolean enumerationEquals = (this.enumeration == null && other.enumeration == null)||
                (this.enumeration != null && this.enumeration.equals(other.enumeration));

        return roomNumberEquals && priceEquals && enumerationEquals;
    }

    @Override

    public int hashCode(){
        return Objects.hash(roomNumber, price, enumeration);
    }

}
