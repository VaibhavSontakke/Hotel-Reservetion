import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;

// when application starts this class will run first and shows option to user
public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        boolean exit = false;

//        this loop will run continuously until we exit application
        while(!exit){
            System.out.println("1. Find and reserve a room\n" +
                    "2. See my reservation\n"+
                    "3. Create an account\n"+
                    "4. Admin\n"+
                    "5. Exit");

            System.out.println("----------------------------------------------------------");
            System.out.println("Write your choice here");
            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    findAndReserveRoom();
                    break;

                case "2":
                    seeMyReservation();
                    break;

                case "3":
                    createAccount();
                    break;

                case "4":
                    openAdminMenu();
                    break;

                case "5":
                    exit = true;
                    break;

                default:
                    System.out.println("Please select choice as per list");
                    System.out.println("----------------------------------------------------------");
            }
        }

    }

//    this function finds the room and reserves for customer
    public  void findAndReserveRoom(){

//      we will first check is customer having account with us or not
        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        Customer customer = AdminResource.getSingleton().getCustomer(email);
        if(customer == null){
            System.out.println("Sorry! but you don't have account with us please first create account");
            return;
        }
//       if user exist then we tell user to enter check in and check out date
        System.out.println("Hey " + customer.getFirstName() + " Welcome! ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        boolean endSession = false;
        Date checkInDate;
        Date checkOutDate;

//        here we are using for loop with try and catch block so errors can be caught in catch block
        for (;;){
            try{
                System.out.println("Enter check in date (dd/mm/yyyy)");
                String date = scanner.nextLine();
                System.out.println(date);
                checkInDate = dateFormat.parse(date);
                break;
            }catch (Exception e){
                System.out.println("Oops! you have entered date in wrong format please enter like this (dd/mm/yyyy)");
            }
        }

        for (;;){
            try{
                System.out.println("Enter check out date (dd/mm/yyyy)");
                String date = scanner.nextLine();
                checkOutDate = dateFormat.parse(date);
                break;
            }catch (Exception e){
                System.out.println("Oops! you have entered date in wrong format please enter like this (dd/mm/yyyy)");
            }
        }
//        then we will find all rooms which is available for given dates using api
        ArrayList<IRoom> rooms = HotelResource.getSingleton().findARoom(checkInDate, checkOutDate);
       ;

//       if available rooms found show this rooms
        if (!rooms.isEmpty()){
            System.out.println("Here is some available rooms as per your requirement :- ");
            for (IRoom room : rooms){
                System.out.println(room);
            }
//        if available rooms are empty we will search for next 7 days and recommended this rooms to customer

        }else if (rooms.isEmpty()){

//          Reference : Stackoverflow
            Calendar calendarIn = Calendar.getInstance();
            Calendar calendarOut = Calendar.getInstance();

            calendarIn.setTime(checkInDate);
            calendarIn.add(Calendar.DAY_OF_MONTH, 7);
            checkInDate = calendarIn.getTime();


            calendarOut.setTime(checkOutDate);
            calendarOut.add(Calendar.DAY_OF_MONTH, 7);
            checkOutDate = calendarOut.getTime();

            ArrayList<IRoom> recommendedRooms = HotelResource.getSingleton().findARoom(checkInDate, checkOutDate);
            if (!recommendedRooms.isEmpty()){
                System.out.println("Sorry! we did not found any available room for given dates\n below is some recommended rooms" +
                        " which is available from " + checkInDate + " to " + checkOutDate);
                System.out.println("----------------------------------------------------------");
                for (IRoom room : recommendedRooms){
                    System.out.println(room);
                }
            }else {
                System.out.println("Sorry! All rooms are booked");
            }

        }

//        if rooms found then we will show the room details to customer and tell him to choose room number
        System.out.println("----------------------------------------------------------");
        System.out.println("If you want to book room from given list, please enter room number");
        String roomNumber = scanner.nextLine();

        ArrayList<Reservation> allReservations= HotelResource.getSingleton().getAllReservations();

//        this function will prevent user to book room which is already booked for given time
        for(Reservation reservation : allReservations){
            if(reservation.getRoom().getRoomNumber().equals(roomNumber) && HotelResource.getSingleton().isReservered(reservation.getRoom(), checkInDate, checkOutDate)){
                System.out.println("Ohh! this room is already booked, please select room from suggested list");
                return;
            }
        }

//        then we will find this room and create new reservation for this customer
        IRoom room = HotelResource.getSingleton().getRoom(roomNumber);

        Reservation reservation = HotelResource.getSingleton().bookARoom(customer, room, checkInDate, checkOutDate);
        System.out.println("----------------------------------------------------------");
        System.out.println("Ola! your reservation is successful\n " +
                "Reservation details:- Room no: " + roomNumber + " from " + checkInDate + " to " + checkOutDate);
        System.out.println("----------------------------------------------------------");
    }

//    this method shows the all reservations of customer
    public void seeMyReservation(){
        System.out.println("Please enter registered email");
        String email = scanner.nextLine();

        ArrayList<Reservation> allReservations = HotelResource.getSingleton().getCustomerReservations(email);

        if (allReservations.isEmpty()){
            System.out.println("Sorry! reservation not found");
            return;
        }

        for (Reservation reservation : allReservations){
            System.out.println(reservation);
        }
    }

//    by this method we create new customer
    public void createAccount(){
        System.out.println("Enter your email");
        String email = scanner.nextLine();

        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();

        HotelResource.getSingleton().createCustomer(firstName, lastName, email);
        System.out.println("Hey! " + firstName + " your account is created successfully, " + email + " this email is linked to your account");
        System.out.println("------------------------------------------------------");
    }

//    this will redirects to admin menu
    public void openAdminMenu(){
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.openAdminMenu();
    }

}
