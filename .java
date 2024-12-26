import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isBooked;
    private String guestName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
        this.guestName = "";
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getGuestName() {
        return guestName;
    }

    public void bookRoom(String guestName) {
        this.isBooked = true;
        this.guestName = guestName;
    }

    public void checkout() {
        this.isBooked = false;
        this.guestName = "";
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + (isBooked ? " (Booked by: " + guestName + ")" : " (Available)");
    }
}

public class HotelManagement {
    private static final ArrayList<Room> rooms = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        System.out.println("Welcome to the Hotel Management System!");

        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> bookRoom();
                case 2 -> checkAvailability();
                case 3 -> checkout();
                case 4 -> viewAllBookings();
                case 5 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i));
        }
    }

    private static void displayMenu() {
        System.out.println("\n==== Hotel Management Menu ====");
        System.out.println("1. Book a room");
        System.out.println("2. Check room availability");
        System.out.println("3. Checkout");
        System.out.println("4. View all bookings");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void bookRoom() {
        System.out.print("Enter room number to book (1-10): ");
        int roomNumber = getRoomNumber();

        if (isValidRoomNumber(roomNumber)) {
            Room room = rooms.get(roomNumber - 1);
            if (!room.isBooked()) {
                System.out.print("Enter guest name: ");
                String guestName = scanner.nextLine();
                room.bookRoom(guestName);
                System.out.println("Room " + roomNumber + " booked successfully for " + guestName + ".");
            } else {
                System.out.println("Room " + roomNumber + " is already booked.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }

    private static void checkAvailability() {
        System.out.println("\n==== Room Availability ====");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private static void checkout() {
        System.out.print("Enter room number to checkout (1-10): ");
        int roomNumber = getRoomNumber();

        if (isValidRoomNumber(roomNumber)) {
            Room room = rooms.get(roomNumber - 1);
            if (room.isBooked()) {
                room.checkout();
                System.out.println("Checked out from room " + roomNumber + " successfully.");
            } else {
                System.out.println("Room " + roomNumber + " is not booked.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }

    private static void viewAllBookings() {
        System.out.println("\n==== Current Bookings ====");
        boolean anyBooking = false;
        for (Room room : rooms) {
            if (room.isBooked()) {
                System.out.println(room);
                anyBooking = true;
            }
        }
        if (!anyBooking) {
            System.out.println("No rooms are currently booked.");
        }
    }

    private static int getRoomNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean isValidRoomNumber(int roomNumber) {
        return roomNumber >= 1 && roomNumber <= 10;
    }
}
