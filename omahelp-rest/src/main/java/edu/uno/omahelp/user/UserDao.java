package edu.uno.omahelp.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
    
    public int createUser(String firstName, String lastName, String email, String password, boolean admin) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql;
        sql = String.format("INSERT INTO \"User\" (first_name, last_name, email, password, is_site_admin) VALUES (%s, %s, %s, %s, %b)", firstName, lastName, email, password, admin);
        return stmt.executeUpdate(sql);
    }

    public boolean update(User user) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql;
        sql = String.format("UPDATE \"User\" SET email = %s AND first_name = %s AND last_name = %s WHERE user_id = %d", user.getEmail(), user.getFirstName(), user.getLastName(), user.getUserId());
        int res = stmt.executeUpdate(sql);

        if(res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public User login(String email, String password) throws Exception {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql;
        sql = String.format("SELECT * FROM \"User\" WHERE email = %s and password = %s", email, password);
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()) {
            int userId = rs.getInt("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            boolean admin = rs.getBoolean("is_site_admin");
            User user = new User(userId, firstName, lastName, email, password, admin);
            return user;
        } else {
            throw new Exception("Invalid Email or Password!");
        }
    }

    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT * FROM \"User\"";
        ResultSet rs = stmt.executeQuery(sql);
        List<User> users = new ArrayList<User>();
        while(rs.next()) {
            int userId = rs.getInt("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            boolean admin = rs.getBoolean("is_site_admin");
            users.add(new User(userId, firstName, lastName, email, password, admin));
        }

        return users;
    }

    public User getUser(int userId) throws URISyntaxException, SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT * FROM \"User\"";
        ResultSet rs = stmt.executeQuery(sql);
        List<User> users = new ArrayList<User>();
        while(rs.next()) {
            userId = rs.getInt("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            boolean admin = rs.getBoolean("is_site_admin");
            return new User(userId, firstName, lastName, email, password, admin);
        }
        return null;
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