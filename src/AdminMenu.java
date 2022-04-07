import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.*;

public class AdminMenu {

//    this class is having all admin controls
    Scanner scanner = new Scanner(System.in);

    public void openAdminMenu(){
        boolean exit = false;

//      while loop continuously runs and show admin menu until stopped
        while(!exit){
            System.out.println("\n1. See all Customers \n"+
                    "2. See all rooms\n"+
                    "3. See all reservations\n"+
                    "4. Add room\n"+
                    "5. Back to main menu");
            System.out.println("--------------------------------------------");
            System.out.println("\nEnter your choice here");

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    seeAllCustomer();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                case "5":
                    exit = true;
                default:
                    System.out.println("Please enter choice as per list");
                    System.out.println("-----------------------------------------\n");
            }
        }
    }

//    this will get list of all customers using api
    public void seeAllCustomer(){
       Collection<Customer> customers = AdminResource.getSingleton().getAllCustomers();
//       if customers collection is empty then this message displayed
       if (customers.isEmpty()){
           System.out.println("Customer not added yet");
           return;
       }

//       else shows all customers using for each loop
       for(Customer customer : customers){
           System.out.println(customer);
       }
    }

//    This will shows all rooms
    public void seeAllRooms(){
        Collection<IRoom> rooms = AdminResource.getSingleton().getAllRooms();
        if (rooms.isEmpty()){
            System.out.println("Rooms not added yet!");
            return;
        }
        for (IRoom room : rooms){
            System.out.println(room);
        }
    }

//    this will display all reservations
    public void seeAllReservations(){
        AdminResource.getSingleton().displayAllReservations();
    }


//  this method will add new room
    public void addRoom(){
        System.out.println("Please enter room number");
        String roomNumber = scanner.nextLine();

        if(HotelResource.getSingleton().checkDuplicateNumber(roomNumber)){
            System.out.println("-----------------------------------------");
            System.out.println("Room with same room number is exist already! please enter another number");
            System.out.println("-----------------------------------------");
            return;
        }

        System.out.println("Please enter room price");
        double price = scanner.nextDouble();

        boolean stop = false;

        RoomType roomType = null;
//        using  while loop for selecting room type
        do {
            System.out.println("Which type of room you want add");
            System.out.println(" 1. Single\n 2. Double ");

            int option = scanner.nextInt();

            if (option == 1){
                roomType = RoomType.Single;
                stop = true;
                break;
            }else if(option == 2) {
                roomType = RoomType.Double;
                stop = true;
                break;
            }else {
                System.out.println("Please select option from menu");
            }

        }while (!stop);

        IRoom room;

//      if price is zero then we will add this room to free room
        if (price == 0.0){
            room = new FreeRoom(roomNumber, roomType);
        }else {
            room = new Room(roomNumber, price, roomType);
        }

        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);
        AdminResource.getSingleton().addRoom(rooms);

        System.out.println("Room added successfully!" + room);
    }

}
