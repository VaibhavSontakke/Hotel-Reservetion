package model;

public class FreeRoom extends Room{
//    this freeRoom class is subclass of Room class this used for creating free room
    public FreeRoom(String roomNumber, RoomType roomType){
//        if admin enters price equal to zero then this free room is created with zero price
        super(roomNumber, 0.0, roomType);
    }


//    using toString method for getting result in proper format
    @Override
    public String toString(){
        return "Room number " + this.getRoomNumber() + " is a " + this.getRoomType() + " is available!";
    }
}
