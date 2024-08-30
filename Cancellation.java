import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cancellation {
	 private String pnrNumber;
	    private User user;

	    // Constructor
	    public Cancellation(String pnrNumber, User user) {
	        this.pnrNumber = pnrNumber;
	        this.user = user;
	    }

	    // Method to cancel the reservation
	    public void cancelReservation(Database db) {
	        String query = "SELECT * FROM reservations WHERE pnr_number = ?";
	        String updateQuery = "UPDATE reservations SET status = 'Cancelled' WHERE pnr_number = ?";

	        try {
	            // Query to find reservation by PNR number
	            ResultSet rs = db.executeQuery(query, pnrNumber);

	            if (rs.next()) {
	                // Update reservation status to 'Cancelled'
	                db.executeUpdate(updateQuery, pnrNumber);
	                System.out.println("Reservation with PNR " + pnrNumber + " has been cancelled.");
	            } else {
	                System.out.println("No reservation found with PNR " + pnrNumber);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error while cancelling reservation.");
	        }
	    }

	    // Static method to create a Cancellation instance from user input
	    public static Cancellation createCancellationFromInput(Database db, User user) {
	    	try (Scanner scanner = new Scanner(System.in)) {
	    	System.out.print("Enter PNR number for cancellation: ");
	    	String pnrNumber = scanner.nextLine();
	    	return new Cancellation(pnrNumber, user);
	    	}
	    	}

	    // Getters and setters
	    public String getPnrNumber() { return pnrNumber; }
	    public void setPnrNumber(String pnrNumber) { this.pnrNumber = pnrNumber; }
	    public User getUser() { return user; }
	    public void setUser(User user) { this.user = user; }


}
