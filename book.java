import java.util.*;

class Cab {
    int cabId;
    String driverName;
    String carModel;
    boolean isAvailable;
    
    Cab(int cabId, String driverName, String carModel, boolean isAvailable) {
        this.cabId = cabId;
        this.driverName = driverName;
        this.carModel = carModel;
        this.isAvailable = isAvailable;
    }
}

class User {
    String username;
    String password;
    
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class CabBookingSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cab> cabs = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static User currentUser = null;

    public static void main(String[] args) {
        // Sample data
        cabs.add(new Cab(1, "Ramesh", "Swift Dzire", true));
        cabs.add(new Cab(2, "Suresh", "Innova", true));
        cabs.add(new Cab(3, "Vijay", "Etios", true));

        int choice;
        do {
            System.out.println("\n==== ONLINE CAB BOOKING SYSTEM ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> System.out.println("Thank you for using Cab Booking System!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }

    static void register() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        users.add(new User(uname, pass));
        System.out.println("✅ Registration successful! Please login to continue.");
    }

    static void login() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.username.equals(uname) && u.password.equals(pass)) {
                currentUser = u;
                System.out.println("✅ Login successful! Welcome " + uname + ".");
                bookingMenu();
                return;
            }
        }
        System.out.println("❌ Invalid credentials. Please try again.");
    }

    static void bookingMenu() {
        int choice;
        do {
            System.out.println("\n---- CAB BOOKING MENU ----");
            System.out.println("1. View Available Cabs");
            System.out.println("2. Book a Cab");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewAvailableCabs();
                case 2 -> bookCab();
                case 3 -> {
                    System.out.println("🚗 Logged out successfully.");
                    currentUser = null;
                }
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }

    static void viewAvailableCabs() {
        System.out.println("\nAvailable Cabs:");
        for (Cab c : cabs) {
            if (c.isAvailable)
                System.out.println("Cab ID: " + c.cabId + " | Driver: " + c.driverName + " | Model: " + c.carModel);
        }
    }

    static void bookCab() {
        System.out.print("Enter pickup location: ");
        String pickup = sc.nextLine();
        System.out.print("Enter drop location: ");
        String drop = sc.nextLine();
        System.out.print("Enter estimated distance (in km): ");
        double distance = sc.nextDouble();
        sc.nextLine();

        viewAvailableCabs();
        System.out.print("Enter Cab ID to book: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Cab c : cabs) {
            if (c.cabId == id && c.isAvailable) {
                double fare = distance * 15; // ₹15 per km
                System.out.println("\nBooking Details:");
                System.out.println("Pickup: " + pickup);
                System.out.println("Drop: " + drop);
                System.out.println("Distance: " + distance + " km");
                System.out.println("Fare: ₹" + fare);
                System.out.println("Driver: " + c.driverName + " (" + c.carModel + ")");
                System.out.println("✅ Cab booked successfully!");
                c.isAvailable = false;
                return;
            }
        }
        System.out.println("❌ Invalid Cab ID or Cab not available.");
    }
}
