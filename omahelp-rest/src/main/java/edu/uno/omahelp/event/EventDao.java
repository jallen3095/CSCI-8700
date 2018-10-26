package edu.uno.omahelp.event;

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
class EventDao {

	public String test() {
		return "From DAO layer!";
	}

    public List<User> listAllEvents() {

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            String sql;
            sql = "SELECT * FROM EVENT";
            ResultSet rs = stmt.executeQuery(sql);
            List<Event> events = new ArrayList<User>();
            while(rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("address");
                String date = rs.getString("date");
                //Event(String name, String description, String date, String location, String area, String[] tags)
                events.add(new Event(email, firstName, lastName, password));
            }

            return events;

        } catch (Exception e) {
            return null;
        }
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
		String dbUrl = "jdbc:postgresql://" + dbURI.getHost() + ':'
                + dbURI.getPort() + dbURI.getPath()
                + "?sslmode=require";
		return DriverManager.getConnection(dbUrl, username, password);
	}
}