package edu.uno.omahelp.event;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.uno.omahelp.user.User;
import edu.uno.omahelp.user.UserDao;

@Component
class EventDao {

	@Autowired
    private UserDao userDao;

    private Connection connection;
    
    public EventDao() throws URISyntaxException, SQLException {
        connection = getConnection();
    }

    public List<Event> listAllEvents() throws URISyntaxException, SQLException {
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT * FROM \"Event\"";
        ResultSet rs = stmt.executeQuery(sql);
        List<Event> events = new ArrayList<>();
        while(rs.next()) {
            events.add(mapEvent(rs));
        }

        return events;
    }

    public List<Event> listMyEvents(int userId) throws URISyntaxException, SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT DISTINCT E.* FROM \"Event\" E INNER JOIN \"Event_User\" EU ON E.event_id = EU.event_id WHERE EU.user_id = ? AND EU.attending = 't'");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        List<Event> events = new ArrayList<>();
        while(rs.next()) {
            events.add(mapEvent(rs));
        }

        return events;
    }

    private Event mapEvent(ResultSet rs) throws SQLException, URISyntaxException {
    	Event event = new Event();
        event.setId(rs.getInt("event_id"));
        event.setName(rs.getString("event_name"));
        event.setLocation(rs.getString("event_address"));
        event.setDate(rs.getString("event_date"));
        event.setDescription(rs.getString("event_description"));
        event.setAttendees(getAttendingUsers(event.getId()));
        event.setInterested(getLikedUsers(event.getId()));
        return event;
    }

    public void createEvent(Event event) throws URISyntaxException, SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event\" (event_name, event_address, event_date, event_description) VALUES (?, ?, ?, ?)");
        stmt.setString(1, event.getName());
        stmt.setString(2, event.getLocation());
        stmt.setDate(3, new Date(Date.parse(event.getDate())));
        stmt.setString(4, event.getDescription());
        stmt.executeUpdate();
    }

    public void editEvent(Event event) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event\" SET event_name = ?, event_address = ?, event_date = ?, event_description = ? WHERE event_id = ?");
        stmt.setString(1, event.getName());
        stmt.setString(2, event.getLocation());
        stmt.setDate(3, new Date(Date.parse(event.getLocation())));
        stmt.setString(4, event.getDescription());
        stmt.setInt(5, event.getId());
        stmt.executeUpdate();
    }

	public void deleteEvent(int eventId) throws URISyntaxException, SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM \"Event\" WHERE event_id = ?");
        stmt.setInt(1, eventId);
        stmt.executeUpdate();
	}

	public boolean hasEventUser(int userId, int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE user_id = ? AND event_id = ?");
        stmt.setInt(1, userId);
        stmt.setInt(2, eventId);
        return stmt.executeQuery().next();
	}

	public void setEventLiked(int userId, int eventId, boolean liked) throws SQLException, URISyntaxException {
		if (hasEventUser(userId, eventId)) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event_User\" SET liked = ? WHERE user_id = ? AND event_id = ?");
            stmt.setBoolean(1, liked);
            stmt.setInt(2, userId);
            stmt.setInt(3, eventId);
            stmt.executeUpdate();
		} else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event_User\" VALUES (%s, %s, %s, %s)");
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setBoolean(3, false);
            stmt.setBoolean(4, liked);
            stmt.executeUpdate();
		}
	}

  public List<User> getLikedUsers(int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE event_id = ? AND liked = 't'");
        stmt.setInt(1, eventId);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
          int userId = rs.getInt("user_id");
          User user = userDao.getUserById(userId);
          if (user != null) {
        	  users.add(user);
          }
        }
        return users;
  }

	public void setEventAttending(int userId, int eventId, boolean attending) throws SQLException, URISyntaxException {
		if (hasEventUser(userId, eventId)) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event_User\" SET attending = ? WHERE user_id = ? AND event_id = ?");
            stmt.setBoolean(1, attending);
            stmt.setInt(2, userId);
            stmt.setInt(3, eventId);
            stmt.executeUpdate();
		} else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event_User\" VALUES (?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setBoolean(3, attending);
            stmt.setBoolean(4, false);
            stmt.executeUpdate();
		}
	}

	public List<User> getAttendingUsers(int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE event_id = ? AND attending ='t'");
        stmt.setInt(1, eventId);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
        	int userId = rs.getInt("user_id");
        	User user = userDao.getUserById(userId);
        	if (user != null) {
        		users.add(user);
        	}
        }
        return users;
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