package edu.uno.omahelp.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This class contains the methods necessary to handle the User database operations
 * requested by the UserController.
 */
@Component
public class UserDao {
    
    /**
     * Performs the actual insertion of a new user into the User database.
     * 
     * @param user A User object to add into the database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public void createUser(User user) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"User\" (first_name, last_name, email, password, is_site_admin) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getPassword());
        stmt.setBoolean(5, user.getAdmin());
        stmt.executeUpdate();
    }

    /**
     * Performs the actual editing of a user in the User database.
     * 
     * @param user A User object with updated field values.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public void editUser(User user) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE \"User\" SET email = ?, first_name = ?, last_name = ? WHERE user_id = ?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getFirstName());
        stmt.setString(3, user.getLastName());
        stmt.setInt(4, user.getUserId());
        stmt.executeUpdate();
    }
    
    /**
     * Performs the actual deletion of a user in the User database.
     * 
     * @param userId The user_id value of the user to delete.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public void deleteUser(int userId) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM \"User\" WHERE user_id = ?");
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    }

    /**
     * Checks to see if an email and password used for logging in is valid.
     * 
     * @param email    The email the user registered with.
     * @param password The user's password.
     * @return A User object corresponding to the user that is logging in.
     * @throws Exception
     */
    public User login(String email, String password) throws Exception {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"User\" WHERE email = ? and password = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            User user = mapUser(rs);
            return user;
        } else {
            throw new Exception("Invalid Email or Password!");
        }
    }

    /**
     * Lists all the users in the User database and puts them in a List.
     * 
     * @return A List object containing a User object for each user in the User database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM \"User\"";
        ResultSet rs = stmt.executeQuery(sql);
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            users.add(mapUser(rs));
        }

        return users;
    }

    /**
     * Returns the User object corresponding the passed in user_id.
     * 
     * @param userId The user_id of the specified user.
     * @return A User object of the specified user.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public User getUserById(int userId) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"User\" WHERE user_id = ?");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            User user = mapUser(rs);

            return user;
        }
        
        return null;
    }
    
    /**
     * Maps the field values from a ResultSet object into a new User object.
     * 
     * @param rs A ResultSet that needs to be converted into a User object.
     * @return A User object representing a row from the User database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    private User mapUser(ResultSet rs) throws URISyntaxException, SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAdmin(rs.getBoolean("is_site_admin"));
        
        return user;
    }
    
    /**
     * Performs the connection to the database.
     * 
     * @return A Connection object for the database connection.
     * @throws URISyntaxException
     * @throws SQLException
     */
    private Connection getConnection() throws URISyntaxException, SQLException {
        URI dbURI = null;

        if(System.getenv("DATABASE_URL") != null) {
            dbURI = new URI(System.getenv("DATABASE_URL"));
        } else {
            String DATABASE_URL = "postgres://wajuhzuzopvjva:15e166e3b55bacf46b2e4040ddd127fa17ac8b5f6da3fd602961415e8ff6273f@ec2-54-225-76-201.compute-1.amazonaws.com:5432/db0fu95pd1fvq";
            dbURI = new URI(DATABASE_URL);
        }

		String username = dbURI.getUserInfo().split(":")[0];
		String password = dbURI.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbURI.getHost() + ':' + dbURI.getPort() + dbURI.getPath() + "?sslmode=require";
        
		return DriverManager.getConnection(dbUrl, username, password);
	}
}