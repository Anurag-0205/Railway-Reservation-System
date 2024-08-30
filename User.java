import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	    private String userId;
	    private String password;
	    private String name;
	    private String email;
	    private Database database; // Database instance

	    // Constructor to initialize User with all details
	    public User(String userId, String password, String name, String email, Database database) {
	        this.userId = userId;
	        this.password = password;
	        this.name = name;
	        this.email = email;
	        this.database = database; // Initialize Database instance
	    }

	    // Method to register a new user
	    public boolean register() {
	        String sql = "INSERT INTO users (user_id, password, name, email) VALUES (?, ?, ?, ?)";
	        try {
	            database.connect(); // Ensure the database connection is open
	            database.executeUpdate(sql, userId, password, name, email);
	            System.out.println("User registered successfully.");
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error registering user.");
	            return false;
	        } finally {
	            database.close(); // Close the connection
	        }
	    }

	    // Method to login a user
	    public boolean login(String inputId, String inputPassword) {
	        String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";
	        try {
	            database.connect(); // Ensure the database connection is open
	            ResultSet rs = database.executeQuery(sql, inputId, inputPassword);
	            boolean isValidUser = rs.next(); // If there is a result, the user is valid
	            rs.close();
	            return isValidUser;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error during login.");
	            return false;
	        } finally {
	            database.close(); // Close the connection
	        }
	    }

	    // Static method to create a User object from user input
	    public static User createUserFromInput(Database database) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter user ID: ");
	        String userId = scanner.nextLine();

	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        System.out.print("Enter name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter email: ");
	        String email = scanner.nextLine();

	        return new User(userId, password, name, email, database);
	    }

	    // Getters and setters for user details
	    public String getUserId() { return userId; }
	    public void setUserId(String userId) { this.userId = userId; }
	    public String getPassword() { return password; }
	    public void setPassword(String password) { this.password = password; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

}
