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

@Component
public class UserDao {
    
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

    public void editUser(User user) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE \"User\" SET email = ?, first_name = ?, last_name = ? WHERE user_id = ?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getFirstName());
        stmt.setString(3, user.getLastName());
        stmt.setInt(4, user.getUserId());
        stmt.executeUpdate();
    }
    
    public void deleteUser(int userId) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM \"User\" WHERE user_id = ?");
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    }

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