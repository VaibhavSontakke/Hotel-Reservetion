package model;

public interface IRoom {

//    creating IRoom interface
//    as we know we can't create room without room number, price, room type and is fre or not
//    so for that we are using interface so subclass need forcefully create this

    public String getRoomNumber();

    public double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();

}
