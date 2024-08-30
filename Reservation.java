import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Reservation {
	 private String reservationId;
	    private User user;
	    private String trainNumber;
	    private String trainName;
	    private String classType;
	    private LocalDate dateOfJourney;
	    private String fromPlace;
	    private String toPlace;
	    private Database database; // Database instance

	    // Constructor
	    public Reservation(String reservationId, User user, String trainNumber, String trainName, String classType, LocalDate dateOfJourney, String fromPlace, String toPlace, Database database) {
	        this.reservationId = reservationId;
	        this.user = user;
	        this.trainNumber = trainNumber;
	        this.trainName = trainName;
	        this.classType = classType;
	        this.dateOfJourney = dateOfJourney;
	        this.fromPlace = fromPlace;
	        this.toPlace = toPlace;
	        this.database = database; // Initialize Database instance
	    }

	    // Method to make a reservation
	    public void makeReservation() {
	        String sql = "INSERT INTO reservations (reservation_id, user_id, train_number, train_name, class_type, date_of_journey, from_place, to_place) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        try {
	            database.connect(); // Ensure the database connection is open
	            database.executeUpdate(sql, reservationId, user.getUserId(), trainNumber, trainName, classType, dateOfJourney, fromPlace, toPlace);
	            System.out.println("Reservation made successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error making reservation.");
	        } finally {
	            database.close(); // Close the connection
	        }
	    }

	    // Method to view a reservation
	    public void viewReservation() {
	        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";

	        try {
	            database.connect(); // Ensure the database connection is open
	            ResultSet rs = database.executeQuery(sql, reservationId);

	            if (rs.next()) {
	                // Retrieve and display reservation details
	                String trainNumber = rs.getString("train_number");
	                String trainName = rs.getString("train_name");
	                String classType = rs.getString("class_type");
	                LocalDate dateOfJourney = rs.getDate("date_of_journey").toLocalDate();
	                String fromPlace = rs.getString("from_place");
	                String toPlace = rs.getString("to_place");

	                System.out.println("Reservation Details:");
	                System.out.println("Train Number: " + trainNumber);
	                System.out.println("Train Name: " + trainName);
	                System.out.println("Class Type: " + classType);
	                System.out.println("Date of Journey: " + dateOfJourney);
	                System.out.println("From Place: " + fromPlace);
	                System.out.println("To Place: " + toPlace);
	            } else {
	                System.out.println("No reservation found with ID " + reservationId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error retrieving reservation.");
	        } finally {
	            database.close(); // Close the connection
	        }
	    }

	    // Static method to create a Reservation instance from user input
	    public static Reservation createReservationFromInput(Database db, User user) {
	        Scanner scanner = new Scanner(System.in);

	        try {
	            System.out.print("Enter Reservation ID: ");
	            String reservationId = scanner.nextLine();

	            System.out.print("Enter Train Number: ");
	            String trainNumber = scanner.nextLine();

	            System.out.print("Enter Train Name: ");
	            String trainName = scanner.nextLine();

	            System.out.print("Enter Class Type: ");
	            String classType = scanner.nextLine();

	            System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
	            LocalDate dateOfJourney = LocalDate.parse(scanner.nextLine());

	            System.out.print("Enter From Place: ");
	            String fromPlace = scanner.nextLine();

	            System.out.print("Enter To Place: ");
	            String toPlace = scanner.nextLine();

	            return new Reservation(reservationId, user, trainNumber, trainName, classType, dateOfJourney, fromPlace, toPlace, db);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error reading input.");
	            return null;
	        } finally {
	            scanner.close(); // Close the scanner
	        }
	    }

	    // Getters and setters
	    public String getReservationId() { return reservationId; }
	    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
	    public User getUser() { return user; }
	    public void setUser(User user) { this.user = user; }
	    public String getTrainNumber() { return trainNumber; }
	    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
	    public String getTrainName() { return trainName; }
	    public void setTrainName(String trainName) { this.trainName = trainName; }
	    public String getClassType() { return classType; }
	    public void setClassType(String classType) { this.classType = classType; }
	    public LocalDate getDateOfJourney() { return dateOfJourney; }
	    public void setDateOfJourney(LocalDate dateOfJourney) { this.dateOfJourney = dateOfJourney; }
	    public String getFromPlace() { return fromPlace; }
	    public void setFromPlace(String fromPlace) { this.fromPlace = fromPlace; }
	    public String getToPlace() { return toPlace; }
	    public void setToPlace(String toPlace) { this.toPlace = toPlace; }


}
